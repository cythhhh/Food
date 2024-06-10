package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    Button read,personal,delete,update,present;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        personal= findViewById(R.id.personal);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, PersonalCenter.class);
                startActivity(intent);
            }
        });

        present = findViewById(R.id.present);
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, PushActivity.class);
                startActivity(intent);
            }
        });

        update = findViewById(R.id.update);
        update.setOnClickListener(this);

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        read = findViewById(R.id.read);
        read.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //通过switch方法跳转到相应界面
        switch (v.getId()) {
            case R.id.update:
                Intent intent_query = new Intent();
                intent_query.setClass(HomePage.this, Update.class);
                startActivity(intent_query);
                break;

            case R.id.delete:
                Intent intent_update = new Intent();
                intent_update.setClass(HomePage.this, RandomImageActivity.class);
                startActivity(intent_update);
                break;

            case R.id.read:
                Intent intent_delete = new Intent();
                intent_delete.setClass(HomePage.this, Circle.class);
                startActivity(intent_delete);
                break;
        }
    }
}