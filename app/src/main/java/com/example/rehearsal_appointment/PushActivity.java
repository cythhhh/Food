package com.example.rehearsal_appointment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.EditText;

import android.widget.Toast;

public class PushActivity extends AppCompatActivity {

    private ImageView uploadButton;
    private EditText foodName, foodLocation, foodTime, recommendation;
    private Button publishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        uploadButton = findViewById(R.id.uploadButton);
        foodName = findViewById(R.id.foodName);
        foodLocation = findViewById(R.id.foodLocation);
        foodTime = findViewById(R.id.foodTime);
        recommendation = findViewById(R.id.recommendation);
        publishButton = findViewById(R.id.publishButton);

        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 处理发布逻辑，将内容传递到 activity_circl
                Toast.makeText(PushActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            uploadButton.setImageURI(imageUri);
        }
    }

}