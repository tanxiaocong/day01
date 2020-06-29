package com.vsc.website.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CommonUtil {

    public static boolean isEmpty(Object o) {
        return o == null;
    }

    public static boolean isEmpty(List o) {
        return o == null || o.size() == 0;
    }

    public static boolean isEmpty(String s) {
        return (s == null || "".equals(s));
    }

    public static boolean isEmptyOrZero(Integer s) {
        return (s == null || s.equals(0));
    }

    public static String getToken() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    public static boolean isToday(Date in) {
        Calendar calendar = Calendar.getInstance();
        int nowY = calendar.get(Calendar.YEAR);
        int nowM = calendar.get(Calendar.MONTH);
        int nowD = calendar.get(Calendar.DATE);
        calendar.setTime(in);
        int comY = calendar.get(Calendar.YEAR);
        int comM = calendar.get(Calendar.MONTH);
        int comD = calendar.get(Calendar.DATE);
        return nowY == comY && nowM == comM && nowD == comD;
    }

    public static boolean beforeToday(Date in) {
        Calendar calendar = Calendar.getInstance();
        int nowY = calendar.get(Calendar.YEAR);
        int nowM = calendar.get(Calendar.MONTH);
        int nowD = calendar.get(Calendar.DATE);
        calendar.setTime(in);
        int comY = calendar.get(Calendar.YEAR);
        int comM = calendar.get(Calendar.MONTH);
        int comD = calendar.get(Calendar.DATE);
        return nowY > comY || nowM > comM || nowD > comD;
    }

    public static boolean afterToday(Date in) {
        Calendar calendar = Calendar.getInstance();
        int nowY = calendar.get(Calendar.YEAR);
        int nowM = calendar.get(Calendar.MONTH);
        int nowD = calendar.get(Calendar.DATE);
        calendar.setTime(in);
        int comY = calendar.get(Calendar.YEAR);
        int comM = calendar.get(Calendar.MONTH);
        int comD = calendar.get(Calendar.DATE);
        return nowY < comY || nowM < comM || nowD < comD;
    }
}
