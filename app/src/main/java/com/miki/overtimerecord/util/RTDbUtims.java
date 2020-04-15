package com.miki.overtimerecord.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.miki.overtimerecord.pojo.RecordTime;

/**
 * author：cai_gp on 2019/11/18 20:10
 */
public class RTDbUtims {

    private static MyDatabaseHelper dbHelper;

    private static MyDatabaseHelper getInstance(Context ctx) {
        if(dbHelper == null) {
            synchronized (RTDbUtims.class) {
                if(dbHelper == null) {
                    return new MyDatabaseHelper(ctx, "RecordTime.db", null, 1);
                }
            }
        }
        return dbHelper;
    }

    public static void put(Context ctx, RecordTime rt) {
        dbHelper = getInstance(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("last_month_overtime", rt.getLastMonthOvertime());
        values.put("now_overtime", rt.getNowOvertime());
        db.insert("Book", null, values);
        values.clear();
    }
}
