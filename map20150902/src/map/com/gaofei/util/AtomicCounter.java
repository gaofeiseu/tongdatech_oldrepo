package com.gaofei.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
	private static AtomicInteger value = new AtomicInteger();
	public static int getValue(){
		return value.get();
	}
	public static int increase(){
		return value.incrementAndGet();
	}
	public static void setValue(int i){
		value.set(i);
	}
}
