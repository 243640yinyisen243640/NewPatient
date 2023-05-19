package com.vice.bloodpressure.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DataUtils {
    public static long currentTimestamp() {
        Date currentDate = new Date();
        return currentDate.getTime();
    }

    /**
     * 获取当前时间字符串
     *
     * @param outFormat 字符串输出时的格式
     * @return
     */
    public static String currentDateString(String outFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(outFormat);
            String dateStr = format.format(new Date());
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";

        }
    }

    /**
     * 把时间字符串转换成Date
     *
     * @param dateString 要转换的字符串
     * @param inFormat   符串的格式，例如yyyy-MM-dd HH：mm:ss
     * @return 果转换成功返回转换以后的date，转换失败的话返回null
     */
    public static Date convertStringToDate(String dateString, String inFormat) {
        SimpleDateFormat format = new SimpleDateFormat(inFormat);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 把一个Date对象转换成相应格式的字符串
     *
     * @param date      时间
     * @param outFormat 输出的格式
     * @return 返回转换的字符串
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertDateToString(Date date, String outFormat) {
        SimpleDateFormat format = new SimpleDateFormat(outFormat);
        return format.format(date);
    }

    public static String getString2DateFormat(String time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //获取当前时间
        Date date = new Date(Long.parseLong(time));
        return simpleDateFormat.format(date);
    }

    public static String getString2DateFormat(Long time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        //获取当前时间
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static List<String> getSevendate() {

        String mYear; // 当前年
        String mMonth; // 月
        String mDay;
        String mWay;
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        for (int i = 0; i < 7; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1))) {
                mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1)));
            }
            String date = mMonth + "/" + mDay;
            dates.add(date);
        }
        return dates;
    }

    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }

    /**
     * 获取今天往后一周的集合
     */
    public static List<String> get7week() {
        String week = "";
        List<String> weeksList = new ArrayList<String>();
        List<String> dateList = get7date();
        for (String s : dateList) {
            if (s.equals(StringData())) {
                week = getWeek(s);
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }

    public static List<String> get7date() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String date = sim.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(java.util.Calendar.DAY_OF_MONTH, 1);
            date = sim.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {


            c.setTime(format.parse(time));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {

        String mYear; // 当前年
        String mMonth; // 月
        String mDay;
        String mWay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }

    /**
     * @param beforeFormat
     * @param laterFormat
     * @param time
     * @return
     * @throws ParseException
     */
    public static String changeDataFormat(String beforeFormat, String laterFormat, String time)  {
        SimpleDateFormat sdf1 = new SimpleDateFormat(beforeFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(laterFormat);
        Date date = null;
        try {
            date = sdf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = sdf2.format(date);
        return format;
    }
}
