package cn.bf.util;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtil {
	public static String getUuid(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		System.out.println(uuid);
		return uuid;
	}
	@Test
	public void test(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		System.out.println(uuid);
	}
}
