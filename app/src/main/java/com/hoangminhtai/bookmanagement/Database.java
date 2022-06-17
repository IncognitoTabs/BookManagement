package com.hoangminhtai.bookmanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import models.Book;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "book_database.sqlite";
    public static final int DB_VERSION=1;

    public static final String TB_NAME = "Book";
    public static final String COL_ID = "BookId";
    public static final String COL_NAME = "BookName";
    public static final String COL_PRICE = "BookPrice";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql= "Create table if not exists " + TB_NAME+"("+COL_ID+" VARCHAR(200) PRIMARY KEY , "+COL_NAME+" VARCHAR(200), "+COL_PRICE+" FLOAT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql= "Drop table if exists " + TB_NAME;
        sqLiteDatabase.execSQL(sql);
    }

    public void execQuery(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }


    private int getNumbOfBook() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TB_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return  count;
    }
    public ArrayList<Book> getListBook() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Book> books = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from "+TB_NAME, null);
        while (cursor.moveToNext()){
            Book b = new Book(cursor.getString(0), cursor.getString(1),cursor.getFloat(2) );
            books.add(b);
        }
        cursor.close();
        return books;
    }

    public void createSomeDefaultBook() {
        int count = this.getNumbOfBook();
        if(count == 0){
            execQuery("insert into "+ TB_NAME+ " values('B01', 'Ngữ Văn 12', 19000)" );
            execQuery("insert into "+ TB_NAME+ " values('B02', 'Đắc Nhân Tâm', 13000)" );
            execQuery("insert into "+ TB_NAME+ " values('B03', 'Ẩm thực được phố', 16000)" );
            execQuery("insert into "+ TB_NAME+ " values('B04', 'Ngôn ngữ lập trình Java', 99000)" );
            execQuery("insert into "+ TB_NAME+ " values('B05', 'Lập trình hướng đối tượng', 22000)" );
            execQuery("insert into "+ TB_NAME+ " values('B06', 'Cấu trúc dữ liệu và giải thuật', 25000)" );
        }
    }
}
