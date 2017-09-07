package com.atguigu.demo.web.test;

/**
 * @author panpala
 * @date 2017年9月7日
 */
public class WebTest {
	
	public static void main(String[] args) {
		String string  = "P60810-141921.jpg";
		String[] split = string.split("\\.");
		for (String string2 : split) {
			System.out.println(string2);
		}
		String path = WebTest.class.getResource("/").getPath();
		System.out.println(path);
	}
	
}
