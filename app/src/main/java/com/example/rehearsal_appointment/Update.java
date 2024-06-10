package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rehearsal_appointment.bean.Rehearsal;
import com.example.rehearsal_appointment.utils.RehearsalOpenHelper;

public class Update extends AppCompatActivity implements View.OnClickListener {

    private EditText etRehearsalId;
    private EditText etBandName;
    private EditText etTime;
    private EditText etPlace;
    private Button btnSearch;
    private Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
    }

    private void initView() {
        etRehearsalId = findViewById(R.id.et_rehearsalid);
        btnSearch = findViewById(R.id.btn_search);
        etBandName = findViewById(R.id.et_bandname);
        etTime = findViewById(R.id.et_time);
        etPlace = findViewById(R.id.et_place);
        btnEdit=  findViewById(R.id.btn_edit);

        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_edit:
                updateOrder();
                break;
        }
    }
    private void searchOrder() {
        String rehearsalid=etRehearsalId.getText().toString().trim();
        RehearsalOpenHelper db=new RehearsalOpenHelper(getApplicationContext());
        db.open();
        Rehearsal re =db.getRehearsal(rehearsalid);
        etBandName.setText(re.bandname);
        etTime.setText(re.time);
        etPlace.setText(re.place);


        db.close();
    }

    private void updateOrder() {
        Rehearsal re=new Rehearsal();
        re.rehearsalId=etRehearsalId.getText().toString().trim();
        re.bandname=etBandName.getText().toString().trim();
        re.time=etTime.getText().toString().trim();
        re.place=etPlace.getText().toString().trim();

        RehearsalOpenHelper db=new RehearsalOpenHelper(getApplicationContext());
        db.open();
        long result= db.updateRehearsal(re);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}