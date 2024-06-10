package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Back extends AppCompatActivity implements View.OnClickListener{

    private Button backfalse;
    private Button backtrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        backfalse = findViewById(R.id.bt_backfalse);
        backfalse.setOnClickListener(this);

        backtrue = findViewById(R.id.bt_backtrue);
        backtrue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_backfalse:
                Intent intent_insert = new Intent();
                intent_insert.setClass(Back.this, PersonalCenter.class);
                startActivity(intent_insert);
                break;

            case R.id.bt_backtrue:
                Intent intent_query = new Intent();
                intent_query.setClass(Back.this, Login.class);
                startActivity(intent_query);
                break;

        }
    }
}