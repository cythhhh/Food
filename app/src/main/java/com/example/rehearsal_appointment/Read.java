package com.example.rehearsal_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.rehearsal_appointment.utils.RehearsalOpenHelper;

import java.util.List;
import java.util.Map;

public class Read extends AppCompatActivity {

    ListView list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        setTitle("查看记录");
        //初始化界面
        init();
    }

    private void init() {
        RehearsalOpenHelper db=new RehearsalOpenHelper(getApplicationContext());
        db.open();
        List<Map<String,Object>> mOrderData=db.getAllRehearsal();
        list=(ListView)findViewById(R.id.lst);
        String[] from={"rehearsalId","bandname","time","place"};
        int[] to={R.id.rehearsalId,R.id.bandname,R.id.time,R.id.place};
        SimpleAdapter listItemAdapter=new SimpleAdapter(Read.this,mOrderData,R.layout.list_new_present,from,to);
        list.setAdapter(listItemAdapter);
        db.close();
    }
}