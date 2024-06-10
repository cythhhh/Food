package com.example.rehearsal_appointment.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RehearsalHelper extends SQLiteOpenHelper {
    public static final String name = "db_Rehearsal.db";
    public static final int DB_VERSION = 1;
    //创建表
    public static final String CREATE_USERDATA1 = "create table tb_Rehearsal(rehearsalId char(5)primary key,bandname varchar(20),time varchar(30),place varchar(20))";
    public RehearsalHelper(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('15725','GunsAndRoses','5月28日  9:00-10:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('78525','PinkFloyed','5月28日  16:00-17:00','音乐厅')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('33614','AC/DC','5月29日  19:00-20:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('56833','Andy_Timmons','5月28日  19:00-20:00','音乐厅')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('66542','Steve_Vai','5月30日  16:00-17:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('33724','梁博','6月1日  19:00-20:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('15132','John_Mayer','6月8日  19:00-20:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('78275','PinkFloyed','6月6日  9:00-10:00','海鸥琴行')");
        db.execSQL("insert into tb_Rehearsal(rehearsalId,bandname,time,place)Values('37788','Ozzy_Osbourne','6月9日  15:00-16:00','海鸥琴行')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
