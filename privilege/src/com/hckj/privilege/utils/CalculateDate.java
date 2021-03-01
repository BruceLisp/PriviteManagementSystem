package com.hckj.privilege.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

public class CalculateDate {
	//自定义的用来计算日期的工具
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	//根据传进来的日期和推后的时间计算推后之后的日期
    public static String calculate(String deadline, int delayDays) {
    	Calendar calendar = Calendar.getInstance();
    	Date date;
		try {
			date = format.parse(deadline);
	    	calendar.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        calendar.add(calendar.DATE, delayDays); 
        deadline = format.format(calendar.getTime());
        return deadline;
	}
    //得到当前的日期
    public static String getCurrentDate() {
    	Date date = new Date(System.currentTimeMillis());
        return format.format(date);
	}
}
