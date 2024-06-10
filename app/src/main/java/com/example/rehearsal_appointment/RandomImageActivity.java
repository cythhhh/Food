package com.example.rehearsal_appointment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class RandomImageActivity extends AppCompatActivity {

    private ImageView randomFoodImage;
    private int[] foodImages = {
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5,
            R.drawable.food6,
            R.drawable.food7,
            R.drawable.food8,
            R.drawable.food9,
            R.drawable.food10
    };
    private int currentFoodIndex = -1; // 用于存储当前显示的图片索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image);

        randomFoodImage = findViewById(R.id.randomFoodImage);
        Button chooseImageButton = findViewById(R.id.chooseImageButton);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRandomImage();
            }
        });

        randomFoodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 根据当前显示的图片索引启动相应的Activity
                if (currentFoodIndex >= 0) {
                    Intent intent = null;
                    switch (currentFoodIndex) {
                        case 0:
                            intent = new Intent(RandomImageActivity.this, Food1Activity.class);
                            break;
                        case 1:
                            intent = new Intent(RandomImageActivity.this, Food2Activity.class);
                            break;
                        // 添加其他case语句以匹配更多的食物图片
                        default:
                            // 如果索引不匹配，可以在这里处理或者不做任何事情
                            break;
                    }
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void animateRandomImage() {
        final Animation outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        final Animation inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 在动画开始时什么都不做
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 当淡出动画结束时，随机选择一个新的图片并开始淡入动画
                Random random = new Random();
                currentFoodIndex = random.nextInt(foodImages.length); // 更新当前图片索引
                randomFoodImage.setImageResource(foodImages[currentFoodIndex]);
                randomFoodImage.startAnimation(inAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // 在动画重复时什么都不做
            }
        });

        // 开始淡出动画
        randomFoodImage.startAnimation(outAnimation);
    }
}
