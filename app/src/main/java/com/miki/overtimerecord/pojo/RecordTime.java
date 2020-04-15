package com.miki.overtimerecord.pojo;

/**
 * author：cai_gp on 2019/11/18 18:39
 */
public class RecordTime {

    private float lastMonthOvertime;

    private float nowOvertime;

    public RecordTime() {
    }

    public RecordTime(float lastMonthOvertime, float nowOvertime) {
        this.lastMonthOvertime = lastMonthOvertime;
        this.nowOvertime = nowOvertime;
    }

    public float getLastMonthOvertime() {
        return lastMonthOvertime;
    }

    public void setLastMonthOvertime(float lastMonthOvertime) {
        this.lastMonthOvertime = lastMonthOvertime;
    }

    public float getNowOvertime() {
        return nowOvertime;
    }

    public void setNowOvertime(float nowOvertime) {
        this.nowOvertime = nowOvertime;
    }
}
