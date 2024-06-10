package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button register,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login= findViewById(R.id.login);
        register=findViewById(R.id.submit);
    }
    public void onClick1(View v){
        //关闭当前页面
        MainActivity.this.finish();
        //跳转到登录界面
        startActivity(new Intent(MainActivity.this, Login.class));
        return;
    }
    public void onClick2(View v){
        //关闭当前页面
        MainActivity.this.finish();
        //跳转到注册界面
        startActivity(new Intent(MainActivity.this, Register.class));
        return;
    }
}