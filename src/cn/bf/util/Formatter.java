package cn.bf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
	
	private static SimpleDateFormat formatter_datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formatter_date = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Date类型转为字符串
	 * 格式：年月日时分秒
	 * @param date
	 * @return
	 */
	public static String toDatetimeString(Date date) {
		if (date == null) {
			return "";
		} else {
			String d = formatter_datetime.format(date);
			return d;
		}
	}
	
	/**
	 * 字符串类型转为Date类型
	 * 格式：年月日
	 * @param source
	 * @return
	 */
	public static Date toDate(String source) {
		if(source == null) {
			return null;
		}
		
		Date date = null;
		try {
	                date = formatter_date.parse(source);
                } catch (ParseException e) {
	                e.printStackTrace();
                }
		return date;
	}
	
	/**
	 * Date类型转为字符串
	 * 格式：年月日
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date) {
		if(date == null) {
			return "";
		} else {
			return formatter_date.format(date);
		}
	}
	
	/**
	 * 如果是null则返回0，否则返回原值
	 * @param i
	 * @return
	 */
	public static int nullToInt(Integer i) {
		if(i == null) {
			return 0;
		} else {
			return i;
		}
	}
}
