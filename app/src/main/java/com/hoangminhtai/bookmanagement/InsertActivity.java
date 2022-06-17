package com.hoangminhtai.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import models.Book;

public class InsertActivity extends AppCompatActivity {

    Button btnSave, btnBack;
    EditText txtID, txtName,txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        linkView();
        addEvent();
    }

    private void addEvent() {
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }
        );
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = txtID.getText().toString().trim();
                String Name = txtName.getText().toString().trim();
                String Price = txtPrice.getText().toString().trim();
                if(Id.isEmpty() || Name.isEmpty() || Price.isEmpty())
                    Toast.makeText(InsertActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                else {
                    MainActivity.db.execQuery("INSERT INTO "+Database.TB_NAME+" VALUES('"+Id+"', '"+Name+"',"+Price+")");
                    Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void linkView() {
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        txtID = findViewById(R.id.edtId);
        txtName = findViewById(R.id.edtName);
        txtPrice = findViewById(R.id.edtPrice);
    }
}