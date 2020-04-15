package com.miki.overtimerecord.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * @author：cai_gp on 2019/11/18 18:37
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    static final String CREATE_RECORDTIME = "create table RecordTime(" +
            "id integer primary key autoincrement," +
            "last_month_overtime float," +
            "now_overtime float)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORDTIME);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


