package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rehearsal_appointment.bean.Rehearsal;
import com.example.rehearsal_appointment.utils.RehearsalOpenHelper;

public class Delete extends AppCompatActivity implements View.OnClickListener{

    private EditText etRehearsalId;
    private EditText etBandname;
    private EditText etTime;
    private EditText etPlace;
    private Button delete,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
    }

    private void initView() {
        etRehearsalId=(EditText)findViewById(R.id.RehearsalId);
        etBandname = (EditText) findViewById(R.id.Bandname);
        delete= (Button) findViewById(R.id.btn_delete);
        search=(Button) findViewById(R.id.btn_search);


        search.setOnClickListener((View.OnClickListener) this);
        delete.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_delete:
                deleteOrder();
                break;
        }
    }
    //查找借书信息
    private void searchOrder() {
        String reid=etRehearsalId.getText().toString().trim();
        RehearsalOpenHelper db=new RehearsalOpenHelper(getApplicationContext());
        db.open();
        Rehearsal re=db.getRehearsal(reid);
        etBandname.setText(re.bandname);
        etTime.setText(re.time);
        etPlace.setText(re.place);

        db.close();
    }
    private void deleteOrder() {
        Rehearsal rehearsal=new Rehearsal();
        rehearsal.rehearsalId=etRehearsalId.getText().toString().trim();
        RehearsalOpenHelper db=new RehearsalOpenHelper(getApplicationContext());
        db.open();
        int result= db.deleteRehearsal(rehearsal);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}