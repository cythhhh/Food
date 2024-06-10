package com.example.rehearsal_appointment.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.rehearsal_appointment.bean.Rehearsal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RehearsalOpenHelper {
    private Context context;
    private RehearsalHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public RehearsalOpenHelper(Context context) {
        this.context = context;
    }

    //打开数据库方法
    public void open() throws SQLiteException {
        dbHelper = new RehearsalHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库方法
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    //添加发布信息内容
    public long addRehearsal(Rehearsal re) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入值
        values.put("RehearsalId", re.rehearsalId);
        values.put("bandname", re.bandname);
        values.put("time", re.time);
        values.put("place", re.place);

        // 通过insert()方法插入数据库中
        return db.insert("tb_Rehearsal", null, values);
    }

    //删除发布信息
    public int deleteRehearsal(Rehearsal re) {
        return db.delete("tb_Rehearsal", "rehearsalId=?", new String[]{String.valueOf(re.rehearsalId)});
    }

    //修改发布信息
    public int updateRehearsal(Rehearsal re) {
        ContentValues value = new ContentValues();
        value.put("bandname", re.bandname);
        value.put("time", re.time);
        value.put("place",re.place);
        return db.update("tb_Rehearsal", value, "rehearsalId=?", new String[]{String.valueOf(re.rehearsalId)});
    }

    //根据游戏id查找以发布内容
    @SuppressLint("Range")
    public Rehearsal getRehearsal(String RehearsalId) {
        Cursor cursor = db.query("tb_Rehearsal", null, "rehearsalId=?", new String[]{RehearsalId}, null, null, null);
        Rehearsal re= new Rehearsal();
        while (cursor.moveToNext()) {
            re.rehearsalId = cursor.getString(cursor.getColumnIndex("rehearsalId"));
            re.bandname = cursor.getString(cursor.getColumnIndex("bandname"));
            re.time = cursor.getString(cursor.getColumnIndex("time"));
            re.place = cursor.getString(cursor.getColumnIndex("place"));

        }
        return re;
    }

    //查找所有发布信息
    @SuppressLint("Range")
    public ArrayList<Map<String, Object>> getAllRehearsal() {
        ArrayList<Map<String, Object>> listRehearsal = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Rehearsal", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("rehearsalId", cursor.getString(cursor.getColumnIndex("rehearsalId")));
                map.put("bandname", cursor.getString(cursor.getColumnIndex("bandname")));
                map.put("time", cursor.getString(cursor.getColumnIndex("time")));
                map.put("place", cursor.getString(cursor.getColumnIndex("place")));

                listRehearsal.add(map);
            }
            return listRehearsal;
        }
    }

    @SuppressLint("Range")
    public int checkTimeRehearsal(String inptCheckString) {
        Cursor cursor = db.query("tb_Rehearsal", new String[]{"time","place"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String time= cursor.getString(0);
            String place= cursor.getString(1);
            String time_place=place+"  "+time;
            if(inptCheckString.equals(time_place)){
                cursor.close();
                db.close();
                return 1;
            }
        }
        return 0;
    }
}
