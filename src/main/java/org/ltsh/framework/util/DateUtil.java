package org.ltsh.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Charles
 * @Description 日期工具类
 */
/**
 * @author Charles
 *
 */
public class DateUtil {
	/**
	 * log4j日志
	 */
	private static final Logger logger = Logger.getLogger(DateUtil.class);
	
	/**
	 * @Description: 日期格式 (2013-01-01 23:59:59)
	 */
	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";	//默认的日期格式，很多地方使用它
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}";	
	/**
	 * @Description: 日期格式 (2013-01-01 23:59)
	 */
	public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";	//默认的日期格式，很多地方使用它
	public static final String PATTERN_YYYY_MM_DD_HH_MM = "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}";	
	/**
	 * @Description: 日期格式 (2013-01-01)
	 */
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String PATTERN_YYYY_MM_DD = "\\d{4}-\\d{1,2}-\\d{1,2}";	
	/**
	 * @Description: 日期格式 (2013-01)
	 */
	public static final String FORMAT_YYYY_MM="yyyy-MM";
	public static final String PATTERN_YYYY_MM = "\\d{4}-\\d{1,2}";	
	/**
	 * @Description: 中国时区
	 */
	public static final String GMT_CN = "GMT+8";
	/**
	 * @Description: 一小时毫秒数
	 */
	private static final long TIMESTAMP_HOUR = 60 * 60 * 1000;
	/**
	 * @Description: 一天的毫秒数
	 */
	private static final long TIMESTAMP_DAY = 24 * TIMESTAMP_HOUR;
	
	private static GregorianCalendar calendar = new GregorianCalendar(Locale.CHINA);
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	
	private DateUtil(){ 
		// 私有构造方法.因为此类是工具类.
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(parse("2013-12-13"));
	}
	/** 
	* @Description: 使用指定日期格式，获取日期字符串的日期
	* @param dateStr
	* @param format
	* @return Date    日期
	*/
	public static Date parse(String dateStr) {
		if(dateStr == null) return new Date();
		String formatStr = null;
		if(dateStr.matches(PATTERN_YYYY_MM)){
			formatStr = FORMAT_YYYY_MM;
		}else if(dateStr.matches(PATTERN_YYYY_MM_DD)){
			formatStr = FORMAT_YYYY_MM_DD;
		}else if(dateStr.matches(PATTERN_YYYY_MM_DD_HH_MM)){
			formatStr = FORMAT_YYYY_MM_DD_HH_MM;
		}else if(dateStr.matches(PATTERN_YYYY_MM_DD_HH_MM_SS)){
			formatStr = FORMAT_YYYY_MM_DD_HH_MM_SS;
		}
		simpleDateFormat.applyPattern(formatStr);
		Date returnDate = null;
		try {
			returnDate =  simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			logger.error("日期字符串"+dateStr+"与格式"+formatStr+"解析错误", e);
		}
		return returnDate;
	}
	/** 
	* @Description: 使用指定日期格式，获取日期的日期字符串
	* @param date
	* @param format
	* @return String   日期字符串
	*/
	public static String format(Date date, String format) {
		if(date == null){
			date = new Date();
		}
		simpleDateFormat.applyPattern(format);
		return simpleDateFormat.format(date);
	}
	/** 
	* @Description: 获取日期的日期字符串
	* @param date
	* @return String   日期字符串
	*/
	public static String format(Date date) {
		return format(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}
	/** 
	* @Description: 获取现在的时间戳
	* @return long    时间戳
	*/
	public static long getNowTimestamp(){
		return new Date().getTime();
	}
	/** 
	 * @Author: Charles
	 * @Description: 获取现在的日期
	 * @return Date: 
	 */
	public static Date getNowDate(){
		return new Date();
	}
	/** 
	 * @Author: Charles
	 * @Description: 获取现在的日期字符串，默认是 yyyy-MM-dd HH:mm:ss格式
	 * @return String: 
	 */
	public static String getNowDateString(){
		return format(new Date(), FORMAT_YYYY_MM_DD_HH_MM_SS);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 将时间戳转为字符串 
	 * @param timestamp
	 * @return String: 
	 */
	public static String timestampToString(long timestamp){
		return timestampToString(timestamp, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}
	/** 
	 * @Author: Charles
	 * @Description: 将时间戳转为字符串 
	 * @param timestamp
	 * @param format	转换格式
	 * @return String: 
	 */
	public static String timestampToString(long timestamp, String format){
		return format(new Date(timestamp), format);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 时间戳转为日期 
	 * @param timestamp
	 * @return Date: 
	 */
	public static Date timestampToDate(long timestamp){
		return new Date(timestamp);
	}

	/** 
	* @Description: 将字符串日期转为时间戳
	* @param dateStr	要转换的日期字符串
	* @return long    时间戳
	*/
	public static long stringToTimestamp(String dateString){
		return parse(dateString).getTime();
	}
	/** 
	 * @Author: Charles
	 * @Description: 将字符串日期转为日期
	 * @param dateString
	 * @return Date: 
	 */
	public static Date stringToDate(String dateString){
		return parse(dateString);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 日期转为字符串
	 * @param date		日期
	 * @return String: 返回格式： yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date){
		return dateToString(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
	}
	/** 
	 * @Author: Charles
	 * @Description: 日期转为字符串
	 * @param date		日期
	 * @param format	格式
	 * @return String: 
	 */
	public static String dateToString(Date date, String format){
		return format(date, format);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 日期转为时间戳 
	 * @param date
	 * @return long: 
	 */
	public static long dateToTimestamp(Date date){
		return date.getTime();
	}
	/** 
	* @Description: 与当前时间比较
	* @param date1	日期
	* @return int    -1为小于当前时间，0为等于当前时间，1为大于当前时间
	*/
	public static int compareToNow(Date date){
		return date.compareTo(new Date());
	}
	
	/** 
	* @Description: 通过日期格式设置calendar并且取出想要的数据
	* @param dateStr
	* @return void    返回类型 
	*/
	private static int getCalendarIndex(String dateStr, int calendarIndex){
		setCalendar(dateStr);
		return calendar.get(calendarIndex);
	}
	/** 
	 * @Author: Charles
	 * @Description: 通过参数设置calendar并且取出想要的数据
	 * @param year
	 * @param month
	 * @param day
	 * @param calendarIndex
	 * @return int: 
	 */
	private static int getCalendarIndex(int year, int month, int day, int calendarIndex){
		setCalendar(year, month, day);	//设置日期
		return calendar.get(calendarIndex);
	}
	
	private static void setCalendar(String dateStr){
		Date date = null;
		if(dateStr == null){
			date = new Date();
		}else{
			date = parse(dateStr);	//格式化指定日期
		}
		calendar.setTime(date);	//设置日期
	}
	private static void setCalendar(Date date){
		calendar.setTime(date);	//设置日期
	}
	
	private static void setCalendar(int year, int month, int day){
		calendar.set(year, month - 1, day);	//设置日期
	}
	
	/** 
	* @Description: 获取今年年份
	* @return int    
	*/
	public static int getNowYear(){
		return getCalendarIndex(null, Calendar.YEAR);
	}
	/** 
	* @Description: 获取这个月份 
	* @return int    月份
	*/
	public static int getNowMonth(){
		int month = getCalendarIndex(null, Calendar.MONTH) + 1;
		return month;
	}
	/** 
	* @Description: 获取当前系统的日期 
	* @return int    日期 
	*/
	public static int getNowDay(){
		return getCalendarIndex(null, Calendar.DAY_OF_MONTH);
	}
	/** 
	 * @Description: 获取系统的小时
	 * @return int: 
	 */
	public static int getNowHour(){
		return getCalendarIndex(null, Calendar.HOUR_OF_DAY);
	}
	/** 
	 * @Description: 获取系统的分钟
	 * @return int: 
	 */
	public static int getNowMinute(){
		return getCalendarIndex(null, Calendar.MINUTE);
	}
	/** 
	 * @Description:  获取系统的秒钟
	 * @return int: 
	 */
	public static int getNowSecond(){
		return getCalendarIndex(null, Calendar.SECOND);
	}
	/** 
	* @Description: 获取这个月的最后一天
	* @return    参数 
	* @return int    返回类型 
	*/
	public static int getLastDayOfMonth() {
		return getLastDayOfMonth(null);
	}
	/** 
	* @Description: 获取某年某月的最后一天
	* @param dateStr	日期字符串
	* @param format		指定日期格式
	* @return int    最后一天的日期
	*/
	public static int getLastDayOfMonth(String dateStr) {
		setCalendar(dateStr);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}
	/** 
	 * @Description: 获取某年某月的最后一天
	 * @param year	年份	
	 * @param month	月份
	 * @return int: 最后一天的日期
	 */
	public static int getLastDayOfMonth(int year, int month) {
		String m = month < 10 ? "0" + month : String.valueOf(month);
		String dateStr = year + "-" + m;
		return getLastDayOfMonth(dateStr);
	}
	/** 
	* @Description: 获取今天是这个月的第几周
	* @return int    周数
	*/
	public static int getWeekOfMonth() {
		return getWeekOfMonth(null);
	}
	/** 
	* @Description: 获取某年月日是该月的第几周
	* @param dateStr	指定日期字符串
	* @return    参数 
	* @return int    第几周
	*/
	public static int getWeekOfMonth(String dateStr) {
		return getCalendarIndex(dateStr, Calendar.WEEK_OF_MONTH);
	}
	/** 
	* @Description: 获取某年月日是该月的第几周
	* @param year	年份
	* @param month	月份
	* @param day	日期
	* @return int    第几周
	*/
	public static int getWeekOfMonth(int year, int month, int day) {
		return getCalendarIndex(year, month, day, Calendar.WEEK_OF_MONTH);
	}
	/** 
	* @Description: 获取今天是星期几
	* @return int    星期几(星期日为0)
	*/
	public static int getDayOfWeek() {
		return getDayOfWeek(null);
	}
	
	/** 
	* @Description: 获取某年月日是星期几
	* @param dateStr: 日期字符串
	* @param format: 日期格式
	* @return int: 星期几(星期日为0)
	*/
	public static int getDayOfWeek(String dateStr) {
		int today = getCalendarIndex(dateStr, Calendar.DAY_OF_WEEK) - 1;
		return today;
	}
	/** 
	* @Description: 获取某年月日是星期几
	* @param year	年份
	* @param month	月份
	* @param day	日期
	* @return int    星期几(星期日为0)
	*/
	public static int getDayOfWeek(int year, int month, int day) {
		int today = getCalendarIndex(year, month, day, Calendar.DAY_OF_WEEK) - 1;
		return today;
	}
	
	/** 
	* @Description: 获取今天是今年里的第几天
	* @return int    第几天
	*/
	public static int getDayOfYear() {
		return getDayOfYear(new Date());
	}
	/** 
	* @Description: 获取某年月日是该年里的第几天
	* @param dateStr: 日期字符串
	* @param format: 日期格式
	* @return int    第几天
	*/
	public static int getDayOfYear(String dateStr) {
		int today = getCalendarIndex(dateStr, Calendar.DAY_OF_YEAR);
		return today;
	}
	/** 
	* @Description: 获取某年月日是该年里的第几天
	* @param year	年份
	* @param month	月份
	* @param day	日期
	* @return int    第几天
	*/
	public static int getDayOfYear(int year, int month, int day) {
		int today = getCalendarIndex(year, month, day, Calendar.DAY_OF_YEAR);
		return today;
	}
	/** 
	 * @Description: 获取某年月日是该年里的第几天
	 * @param date	日期
	 * @return int: 第几天
	 */
	public static int getDayOfYear(Date date) {
		setCalendar(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	/** 
	 * @Description: 计算某年月日与今天相差多少天(某年月日期-当前时间)
	 * @param prevDate	要计算的日期
	 * @return int: 相差多少天
	 */
	public static int getCountDay(Date date){
		return getCountDay(new Date(), date);
	}
	/** 
	 * @Description: 计算某年月日与今天相差多少天(指定日期-当前时间)
	 * @param dateStr	日期字符串
	 * @return int: 	相差多少天
	 */
	public static int getCountDay(String dateStr){
		Date date = parse(dateStr);
		return getCountDay(new Date(), date);
	}
	/** 
	 * @Description: 计算两个日期相差多少天 (后面参数日期-前面参数日期)
	 * @param inDate	日期
	 * @param nowDate	日期
	 * @return long: 相差多少天
	 */
	public static int getCountDay(Date prevDate, Date nextDate){
		return getCountDay(prevDate.getTime(), nextDate.getTime());
	}
	/** 
	 * @Description: 计算两个日期相差多少天 (后面参数日期-前面参数日期)
	 * @param prevDateStr	日期字符串
	 * @param nextDateStr	日期字符串
	 * @return long: 	相差天数
	 */
	public static int getCountDay(String prevDateStr, String nextDateStr){
		Date prevDate = parse(prevDateStr);
		Date nextDate = parse(nextDateStr);
		return getCountDay(prevDate, nextDate);
	}
	/** 
	 * @Description: 计算两个日期相差多少天 (后面参数日期-前面参数日期)
	 * @param inDate	日期
	 * @param nowDate	日期
	 * @return long: 相差多少天
	 */
	public static int getCountDay(long prevDateTimestamp, long nextDateTimestamp){
		long countTimestamp = nextDateTimestamp - prevDateTimestamp;
		int count = (int)(Math.abs(countTimestamp) / TIMESTAMP_DAY) ; 	//计算两个日期之前相差多少天
		return count;
	}
}
