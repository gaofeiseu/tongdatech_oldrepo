package com.gaofei.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolFactory {
	private static int cpuNums = 0;
	private static final int POOL_SIZE = 50;
	static{
		cpuNums = Runtime.getRuntime().availableProcessors();
	}
	private static ExecutorService executor;
	private static List<Future<Object>> list_future = new ArrayList<Future<Object>>();
	
	public static void startUpThreadPool(){
		clearListFuture();
		executor = Executors.newFixedThreadPool(cpuNums * POOL_SIZE);
	}
	/**
	 * ���̳߳��������
	 * @param mapTask
	 */
	public static void addMapTaskIntoThreadPool(MapTask mapTask){
		list_future.add(executor.submit(mapTask));
	}
	/**
	 * ����ִ��֮��Ľ����
	 * @return
	 */
	public static List<Future<Object>> getListFuture(){
		return list_future;
	}
	/**
	 * ��ճ�ʼ��ִ��֮��Ľ����
	 */
	private static void clearListFuture(){
		list_future = new ArrayList<Future<Object>>();
	}
	/**
	 * �����ر��̳߳�����
	 */
	public static void shutDownThreadPool(){
		executor.shutdown();
	}
	/**
	 * ���������ر��̳߳ص�����
	 * @return
	 */
	public static List<Runnable> shutDownThreadPoolNow(){
		return executor.shutdownNow();
	}
	
}
