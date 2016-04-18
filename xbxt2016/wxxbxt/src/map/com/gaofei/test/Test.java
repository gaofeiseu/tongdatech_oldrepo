package com.gaofei.test;

public class Test {
	public static void main(String[] args) {
		String s = String.format("http://mt%d.google.cn/vt/lyrs=m@259000000&hl=zh-CN&gl=CN&src=app&x=%d&y=%d&z=%d&s=Galileo", 1,2,3,4);
		System.out.println(s);
	}
}
