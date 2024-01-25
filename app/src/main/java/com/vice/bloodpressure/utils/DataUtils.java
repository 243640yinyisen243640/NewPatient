package com.vice.bloodpressure.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
