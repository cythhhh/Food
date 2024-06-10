package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Food1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局文件
        setContentView(R.layout.activity_food1);

        // 获取 FrameLayout 容器
        //FrameLayout container = findViewById(R.id.container);

        // 假设您有另一个布局文件，名为 activity_content，
        // 它将用于显示登录界面或其他内容
        // 由于我们要显示 activity_circle 的界面，这里不需要加载其他布局
        // 因此，我们不需要调用 getLayoutInflater().inflate(R.layout.activity_content, container);
    }
}
