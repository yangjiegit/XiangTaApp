package com.muse.chat.utils;

import com.muse.xiangta.CuckooApplication;
import com.muse.xiangta.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间转换工具
 */
public class TimeUtil {


    private TimeUtil(){}


    public static String getTimeStr(String time){
        try {
            return getTimeStr(Long.parseLong(time));
        }catch (Exception e){

        }
        return "- -";
    }

    public static String format(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");

        try {
            return sdf.format(new Date(Long.parseLong(time)*1000));
        }catch (Exception e){

        }
        return "";
    }

    public static String getTimeStr(long timeStamp){
        if (timeStamp==0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp*1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        if (calendar.before(inputTime)){
            //今天23:59在输入时间之前，解决一些时间误差，把当天时间显示到这里
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + CuckooApplication.getInstances().getResources().getString(R.string.time_year)+"MM"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"dd"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day));
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        if (calendar.before(inputTime)){
            return CuckooApplication.getInstances().getResources().getString(R.string.time_yesterday);
        }else{
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)){
                SimpleDateFormat sdf = new SimpleDateFormat("M"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"d"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day));
                return sdf.format(currenTimeZone);
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + CuckooApplication.getInstances().getResources().getString(R.string.time_year)+"MM"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"dd"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day));
                return sdf.format(currenTimeZone);

            }

        }

    }

    /**
     * 时间转化为聊天界面显示字符串
     *
     * @param timeStamp 单位为秒
     */
    public static String getChatTimeStr(long timeStamp){
        if (timeStamp==0) return "";
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTimeInMillis(timeStamp*1000);
        Date currenTimeZone = inputTime.getTime();
        Calendar calendar = Calendar.getInstance();
        if (!calendar.after(inputTime)){
            //当前时间在输入时间之前
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + CuckooApplication.getInstances().getResources().getString(R.string.time_year)+"MM"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"dd"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day));
            return sdf.format(currenTimeZone);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (calendar.before(inputTime)){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(currenTimeZone);
        }
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        if (calendar.before(inputTime)){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return CuckooApplication.getInstances().getResources().getString(R.string.time_yesterday)+" "+sdf.format(currenTimeZone);
        }else{
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            if (calendar.before(inputTime)){
                SimpleDateFormat sdf = new SimpleDateFormat("M"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"d"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day)+" HH:mm");
                return sdf.format(currenTimeZone);
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+ CuckooApplication.getInstances().getResources().getString(R.string.time_year)+"MM"+ CuckooApplication.getInstances().getResources().getString(R.string.time_month)+"dd"+ CuckooApplication.getInstances().getResources().getString(R.string.time_day)+" HH:mm");
                return sdf.format(currenTimeZone);
            }

        }

    }
}
