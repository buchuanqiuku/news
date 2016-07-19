package com.buchuanqiuku.demo.news.utils;

/**
 * Created by lipeidong on 2016/7/3.
 */
public class StringUtils {
    public static String getNumberFormat(int number){
        String format=null;
        if(number<10000){
            format=number+"";
        }else{
            //去除万位后的值
            float a =number/10000+(float)((number%10000)/1000)/10;
            format=a+"万";
        }

        return format;
    }

    public static String getLunboFormat(String abc){
        return abc.substring(4,8)+"/"+abc.substring(9);

    }
}
