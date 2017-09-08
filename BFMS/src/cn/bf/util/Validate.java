package cn.bf.util;

import java.util.Scanner;

import org.junit.Test;

/**
 * 表单校验工具类
 * 
 * @author WYC
 * 
 */
public class Validate {

	// 判断是否是数字
	private static String numRegex = "^[0-9]*$";

	// 判断是否是中文
	private static String zhRegex = "^[\u4e00-\u9fa5]+$";
	
	// 判断是否是日文
	private static String jpRegex = "/u0800-/u4e00";
	
	// 判断是否是正小数（1或2位小数）
	private static String decimalRegex = "^[0-9]{1,2}(\\.[0-9]{1,2})*$";
	
	// 判断特殊字符
	private static String refuseRegex = "^[A-Za-z0-9\u4e00-\u9fa5]+.(jpg|JPG|png|PNG|jpeg|JPEG)$";
	
	// 判断血型
	private static String bloodRegex = "^(A|B|AB|O)$";

	/**
	 * 判断是否是数字
	 * @param num
	 * @return
	 */
	public static boolean isNum(String num) {
		if (num.matches(numRegex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否是中文
	 * @param zh
	 * @return
	 */
	public static boolean isZh(String zh) {
		if (zh.matches(zhRegex)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串长度
	 * @param source
	 * @param len
	 * @return
	 */
	public static boolean isLength(String source, long len) {
		if (source.length() >= 0 && source.length() <= len) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否是日文
	 * @param jp
	 * @return
	 */
	public static boolean isJp(String jp) {
		if(jp.matches(jpRegex)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断数字范围
	 * @param source
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean numRange(Long source, Long begin, Long end) {
		if(source > begin && source < end) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否是正小数（2位小数）
	 * @param source
	 * @return
	 */
	public static boolean isDecimal(Float source) {
		if((source + "").matches(decimalRegex)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断是否包含特殊字符
	 * @param source
	 * @return
	 */
	public static boolean isRefuseChar(String source) {
		if(source.matches(refuseRegex)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断血型
	 * @param source
	 * @return
	 */
	public static boolean isBlood(String source) {
		if(source.matches(bloodRegex)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	@Test
	public void test(){
		String rex = "^[0-9]{1,2}(\\.[0-9]{1,2})*$";
		while(true) {
			@SuppressWarnings("resource")
                        String s = new Scanner(System.in).next();
			System.out.println(s.matches(rex));
		}
	}
}
