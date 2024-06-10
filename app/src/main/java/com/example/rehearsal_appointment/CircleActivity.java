package com.example.rehearsal_appointment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CircleActivity extends AppCompatActivity {

    private ImageView foodImage;
    private TextView foodName, foodLocation, foodTime, recommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
//
//        foodImage = findViewById(R.id.food1);
//        foodName = findViewById(R.id.foodName);
//        foodLocation = findViewById(R.id.foodLocation);
//        foodTime = findViewById(R.id.foodTime);
//        recommendation = findViewById(R.id.recommendation);

        // 这里可以添加逻辑来从 PushActivity 接收并显示信息
        // 例如，使用 intent 传递的对象来设置 TextView 的内容

        // 示例：从 intent 获取数据并设置 TextView
        String name = getIntent().getStringExtra("foodName");
        String location = getIntent().getStringExtra("foodLocation");
        String time = getIntent().getStringExtra("foodTime");
        String reason = getIntent().getStringExtra("recommendation");

        foodName.setText(name);
        foodLocation.setText(location);
        foodTime.setText(time);
        recommendation.setText(reason);
    }
}
