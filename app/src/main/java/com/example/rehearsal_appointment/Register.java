package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText inptName,inptPwd;
    private Button register;
    private String userName,passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    public void init(){
        //给按钮匹配id
        inptName = (EditText)findViewById(R.id.username);
        inptPwd = (EditText)findViewById(R.id.pwd);
        register = (Button)findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                //提示输入信息
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(passWord)){
                    Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(userName)){
                    Toast.makeText(Register.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();

                    //把保存账号密码
                    saveRegisterInfo(userName, passWord);
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);

                    //跳转到登录界面中
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    Register.this.finish();
                }
            }
        });
    }
    //获得已输入信息
    private void getEditString(){
        userName = inptName.getText().toString().trim();
        passWord = inptPwd.getText().toString().trim();
    }
    //判断输入的用户名是否已经存在
    private boolean isExistUserName(String userName){
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        //判断密码是否为空，不空则注册成功
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    //将用户名和密码保存到sp中
    private void saveRegisterInfo(String userName,String psw){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName, psw);
        editor.commit();
    }
    //跳转回登录界面
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Register.this,Login.class);
        startActivity(intent);
        Register.this.finish();
    }
}