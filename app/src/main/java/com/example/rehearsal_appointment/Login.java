package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button login;
    private TextView tv_register;
    private EditText inptName,inptPwd;
    private CheckBox save_pwd;
    private String userName,passWord,spPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init() {
        //给所有按钮匹配id
        inptName = (EditText) findViewById(R.id.username);
        inptPwd = (EditText) findViewById(R.id.pwd);
        save_pwd = (CheckBox) findViewById(R.id.save_pwd);
        login = (Button)findViewById(R.id.loginBtn);
        tv_register = (TextView) findViewById(R.id.submit);
        //获取记住的账号密码
        getUserInfo();
        //登录方法
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();// 定义方法 readPsw为了读取用户名，得到密码
                spPsw = readPsw(userName);
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText( Login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(passWord)){
                    Toast.makeText( Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                    // md5Psw.equals(); 判断密码是否与注册的一致
                }else if(passWord.equals(spPsw)){
                    //一致登录成功
                    Toast.makeText( Login.this, "welcome！"+ userName, Toast.LENGTH_SHORT).show();
                    //保存登录状态，在界面保存登录的用户名和密码
                    saveLoginInfo(userName,passWord);
                    saveLoginStatus(true, userName);
                    //登录成功后关闭此页面进入主页
                    Intent data = new Intent();
                    data.putExtra("isLogin",true);
                    setResult(RESULT_OK,data);
                    //关闭登录界面
                    Login.this.finish();
                    //跳转到下一个界面
                    startActivity(new Intent( Login.this, HomePage.class));
                    return;
                    //如果用户名或者账号错误会提示错误
                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!passWord.equals(spPsw))){
                    Toast.makeText( Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText( Login.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面
                Intent intent=new Intent( Login.this,Register.class);
                startActivity(intent);
                Login.this.finish();
            }
        });
    }
    //获取用户名和密码
    private void getEditString(){
        userName = inptName.getText().toString().trim();
        passWord = inptPwd.getText().toString().trim();
    }
    //保存登录信息
    public void saveLoginInfo(String userName, String passWord){
        //获取SharedPreferences对象
        boolean CheckBoxLogin = save_pwd.isChecked();
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //设置参数
        if (CheckBoxLogin){
            editor.putString("username", userName);
            editor.putString("password", passWord);
            editor.putBoolean("checkboxBoolean",true);
            editor.commit();
        }else {
            editor.putString("username", null);
            editor.putString("password", null);
            editor.putBoolean("checkboxBoolean", false);
            editor.commit();
        }
    }
    //从已经存入的对象中读取密码
    private String readPsw(String userName){
        SharedPreferences sp  = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }
    //保存登录状态
    private void saveLoginStatus(boolean status,String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.commit();
    }
    //返回注册成功数据
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                inptName.setText(userName);
                inptName.setSelection(userName.length());
            }
        }
    }
    //获得用户已注册的信息
    public void getUserInfo(){
        SharedPreferences sp = null;
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if (sp.getBoolean("checkboxBoolean", false))
        {
            inptName.setText(sp.getString("username", null));
            inptPwd.setText(sp.getString("password", null));
            save_pwd.setChecked(true);
        }else{
            inptName.setText(sp.getString("username", userName));
            inptPwd.setText(sp.getString("password", passWord));
            save_pwd.setChecked(false);
        }
    }
    public void onBackPressed() {
        Login.this.finish();
    }
}