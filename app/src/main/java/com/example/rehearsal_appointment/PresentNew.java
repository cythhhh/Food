package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rehearsal_appointment.bean.Rehearsal;
import com.example.rehearsal_appointment.utils.RehearsalOpenHelper;


public class PresentNew extends AppCompatActivity implements View.OnClickListener {

    private EditText etRehearsalId,etBandname,etDate;
    private Spinner Time,Place;
    private Button btnAdd;
    String placeText,timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_new);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        etRehearsalId=(EditText)findViewById(R.id.RehearsalId);
        etBandname = (EditText) findViewById(R.id.Bandname);
        etDate = (EditText) findViewById(R.id.Date);
        Time = (Spinner) findViewById(R.id.Time);
        Place = (Spinner) findViewById(R.id.Place);

        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);

        Place.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                placeText=PresentNew.this.getResources().getStringArray(R.array.choose_place)[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Time.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeText=PresentNew.this.getResources().getStringArray(R.array.choose_time)[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String rehearsalId = etRehearsalId.getText().toString().trim();
        String bandname = etBandname.getText().toString().trim();
        String time = timeText;
        String place = placeText;
        String dateText = etDate.getText().toString().trim();
        //检验信息是否正确
        if (TextUtils.isEmpty(rehearsalId)) {
            Toast.makeText(this, "请输入排练号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bandname)) {
            Toast.makeText(this, "请输入乐队名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "请选择排练时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(place)) {
            Toast.makeText(this, "请选择排练场地", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(dateText)) {
            Toast.makeText(this, "请输入排练日期", Toast.LENGTH_SHORT).show();
            return;
        }

        String timeString = dateText + "  " + time;
        String checkString = place +"  "+ timeString;

        RehearsalOpenHelper db = new RehearsalOpenHelper(getApplicationContext());
        db.open();

        int check = db.checkTimeRehearsal(checkString);
        if (check == 0) {
            //添加信息
            Rehearsal re = new Rehearsal();
            re.rehearsalId = rehearsalId;
            re.bandname = bandname;
            re.time = timeString;
            re.place = place;

            //创建数据库访问对象

            long result = db.addRehearsal(re);

            if (result > 0) {
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
            }
            db.close();
            finish();
        }
        else{
            Toast.makeText(this, "该场地本时间段已经被预约", Toast.LENGTH_SHORT).show();
        }
    }
}