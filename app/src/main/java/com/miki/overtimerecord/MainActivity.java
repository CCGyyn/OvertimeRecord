package com.miki.overtimerecord;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.miki.overtimerecord.util.SPUtils;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author cai_gp
 * on ${DATE}$
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 记录加班时间
     */
    Button btnRecord;
    /**
     * 这个月加班累计
     */
    TextView tvTimeOver;
    /**
     * 上个月加班时间
     */
    TextView tvLastTime;

    Context mContext;

    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRecord = (Button)findViewById(R.id.btn_record);
        tvTimeOver = (TextView)findViewById(R.id.tv_time_over);
        tvLastTime = (TextView)findViewById(R.id.tv_last_time);

        btnRecord.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if(day != 1) {
                SPUtils.put(mContext, "is_reset", false);
                caculateOverTime(calendar);
            } else {
                boolean isReset = SPUtils.getBoolean(mContext, "is_reset");
                if(!isReset) {
                    // 已加班时间
                    int haveOtHour = SPUtils.getInt(mContext, "haveOtHour");
                    int haveOtMinute = SPUtils.getInt(mContext, "haveOtMinute");
                    String lastMonthOvertime = haveOtHour + "-" + haveOtMinute;
                    SPUtils.put(mContext, "lastMonthOvertime", lastMonthOvertime);
                    SPUtils.put(mContext, "haveOtHour", 0);
                    SPUtils.put(mContext, "haveOtMinute", 0);
                    caculateOverTime(calendar);
                    SPUtils.put(mContext, "is_reset", true);

                }
            }
            int hOtHour = SPUtils.getInt(mContext, "haveOtHour");
            int hOtMinute = SPUtils.getInt(mContext, "haveOtMinute");

            tvTimeOver.setText(hOtHour + "-" + hOtMinute);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String lastMonthOvertime = SPUtils.getString(mContext, "lastMonthOvertime");
        if(lastMonthOvertime != null && !lastMonthOvertime.equals("")) {
            tvLastTime.setText(lastMonthOvertime);
        }

        // 已加班时间
        int hOtHour = SPUtils.getInt(mContext, "haveOtHour");
        int hOtMinute = SPUtils.getInt(mContext, "haveOtMinute");

        tvTimeOver.setText(hOtHour + "-" + hOtMinute);
    }

    private void caculateOverTime(Calendar calendar) {
        // 24小时制
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        // 加班时间 分钟
        int otMinute = 0;
        // 已加班时间
        int haveOtHour = SPUtils.getInt(mContext, "haveOtHour");
        int haveOtMinute = SPUtils.getInt(mContext, "haveOtMinute");
        int tempMinute = 0;
        if(getWeekOfDate(new Date()).equals(weekDays[6]) && hour >= 16) {
            otMinute += hour*60 + minute - 14*60 - 30;
        } else if(hour >= 21){
            otMinute += hour*60 + minute - 18*60 - 30;
        }
        haveOtHour += otMinute / 60;
        haveOtMinute += otMinute % 60;
        haveOtHour += haveOtMinute / 60;
        haveOtMinute = haveOtMinute % 60;
        SPUtils.put(mContext, "haveOtHour", haveOtHour);
        SPUtils.put(mContext, "haveOtMinute", haveOtMinute);
    }


    public String getWeekOfDate(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}
