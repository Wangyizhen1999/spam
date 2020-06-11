package com.hrbuedu.cn.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class DateTest {
	//上周一
	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	
	//上周日
	public static Date getLastWeekSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
 
	//本周一
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
 
	//下周一
//	public static Date getNextWeekMonday(Date date) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(getThisWeekMonday(date));
//		cal.add(Calendar.DATE, 7);
//		return cal.getTime();
//	}

	//昨天
	public static Date getYesterday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
 
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = new Date();
			String a = "2020-06-07";
//			String lastWeekMonday = 0;
			if (a.equals(sdf.format(geLastWeekMonday(date)))) {
				System.out.println("上周一");
			}else if (a.equals(sdf.format(getThisWeekMonday(date)))) {
				System.out.println("这周一");
			}else if (a.equals(sdf.format(getYesterday(date)))){
				System.out.println("昨天");
			}else if (a.equals(sdf.format(new Date()))) {
				System.out.println("今天");
			}else {
				System.out.println("啥也不是");
			}
			System.out.println("今天是" + sdf.format(date));
			System.out.println("上周一" + sdf.format(geLastWeekMonday(date)));
			System.out.println("上周日" + sdf.format(getLastWeekSunday(date)));
			System.out.println("本周一" + sdf.format(getThisWeekMonday(date)));
//			System.out.println("下周一" + sdf.format(getNextWeekMonday(date)));
			System.out.println("昨天是" + sdf.format(getYesterday(date)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
