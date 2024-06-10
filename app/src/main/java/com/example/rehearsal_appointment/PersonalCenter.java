package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalCenter extends AppCompatActivity implements View.OnClickListener {

    TextView LoginNumber;
    private Button logout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);
        //取出登录时的登录名
        LoginNumber = findViewById(R.id.tv_student_number);
        String StuNumber = this.getIntent().getStringExtra("username1");
        LoginNumber.setText(StuNumber);
        //返回主界面
        Button btnBack = findViewById(R.id.bt_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                Intent intent_insert = new Intent();
                intent_insert.setClass(PersonalCenter.this, Back.class);
                startActivity(intent_insert);
                break;
        }
    }
}