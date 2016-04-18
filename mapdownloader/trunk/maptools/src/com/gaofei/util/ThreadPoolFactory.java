package com.gaofei.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolFactory {
//	private static int cpuNums = 0;
	private static final int POOL_SIZE = 200;
//	static{
//		cpuNums = Runtime.getRuntime().availableProcessors();
//	}
	private static ExecutorService executor;
	private static List<Future<Object>> list_future = new ArrayList<Future<Object>>();
	
	public static void startUpThreadPool(){
		clearListFuture();
		executor = Executors.newFixedThreadPool(POOL_SIZE);
	}
	/**
	 * 给线程池添加任务
	 * @param mapTask
	 */
	public static void addMapTaskIntoThreadPool(MapTask mapTask){
		list_future.add(executor.submit(mapTask));
	}
	/**
	 * 返回执行之后的结果集
	 * @return
	 */
	public static List<Future<Object>> getListFuture(){
		return list_future;
	}
	/**
	 * 清空初始化执行之后的结果集
	 */
	private static void clearListFuture(){
		list_future = new ArrayList<Future<Object>>();
	}
	/**
	 * 发出关闭线程池请求
	 */
	public static void shutDownThreadPool(){
		executor.shutdown();
	}
	/**
	 * 发出立即关闭线程池的请求
	 * @return
	 */
	public static List<Runnable> shutDownThreadPoolNow(){
		return executor.shutdownNow();
	}
	
}
