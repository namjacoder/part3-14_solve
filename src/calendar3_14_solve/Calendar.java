package calendar3_14_solve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;

public class Calendar {
	private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int[] LEAP_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private HashMap<Date, String> planMap;
	
	public Calendar() {
		planMap = new HashMap<Date, String>();
	}
	
	/*
	 * @param date ex: "2017-06-20"
	 * @param plan
	 */
	public void registerPlan(String strDate, String plan) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		planMap.put(date, plan);
	}
	
	public String searchPlan(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		String plan = planMap.get(date);
		return plan;
	}
	
	public boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 !=0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getMaxDaysOfMonth(int year, int month) {
		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[month];
		} else {
			return MAX_DAYS[month];
		}
	}
	
	public void printCalendar() {
		
	}
	
	private int getWeekDay(int year, int month, int day) {
		int syear = 1970;
		final int STANDARD_WEEKDAY = 4;
		
		int count = 0;
		
		for (int i=syear; i<year; i++) {
			int delta = isLeapYear(i) ? 366:365;
			count += delta;
		}
		
		for (int i=1; i<month; i++) {
			int delta = getMaxDaysOfMonth(year, i);
			count += delta;
		}
		
		count += day - 1;
		
		int weekday = (count + STANDARD_WEEKDAY) % 7;
		return weekday;
	}
	
	public static void main(String[] args) throws ParseException {
		Calendar cal = new Calendar();
		System.out.println(cal.getWeekDay(1970, 1, 1) == 4);
		System.out.println(cal.getWeekDay(1971, 1, 1) == 5);
		System.out.println(cal.getWeekDay(1972, 1, 1) == 6);
		System.out.println(cal.getWeekDay(1973, 1, 1) == 1);
		System.out.println(cal.getWeekDay(1974, 1, 1) == 2);
		
		cal.registerPlan("2021-06-06", "Let's eat beef!");
		System.out.println(cal.searchPlan("2021-06-06").equals("Let's eat beef!"));
	}
}
