package com.hoangminhtai.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import adapter.BookAdapter;
import models.Book;

public class MainActivity extends AppCompatActivity {

    ListView lvBook;

    ArrayList<Book> initList;
    BookAdapter adapter;

    public static Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBook = findViewById(R.id.lvBook);

        prepareDB();
        loadData();
        addEvent();
    }

    private void addEvent() {
        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void prepareDB() {
        db = new Database(MainActivity.this);
        db.createSomeDefaultBook();
    }

    private void loadData() {
        initList = new ArrayList<>();
        initList = db.getListBook();
        adapter = new BookAdapter(MainActivity.this,initList);
        lvBook.setAdapter(adapter);
    }
}