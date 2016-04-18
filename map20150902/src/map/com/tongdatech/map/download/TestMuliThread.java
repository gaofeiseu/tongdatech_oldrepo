package com.tongdatech.map.download;

import java.util.ArrayList;
import java.util.List;

public class TestMuliThread {
	private static final double x1 = 120.23540496826172;
	private static final double x4 = 120.39848327636719;
	private static final double y1 = 31.56712782875264;
	private static final double y4 = 31.46439667841802;
	@SuppressWarnings("unused")
	private static final int zoom_st = 1;
	@SuppressWarnings("unused")
	private static final int zoom_ed = 17;
	String file_path = "";
	public List<String> howManyThreadWeNeed(String outimg_x1,String outimg_y1,String outimg_x4,String outimg_y4,String ed_zoom){
		List<String> thread_param_list = new ArrayList<String>();
		double x1 = Double.valueOf(outimg_x1);
		double y1 = Double.valueOf(outimg_y1);
		double x4 = Double.valueOf(outimg_x4);
		double y4 = Double.valueOf(outimg_y4);
		String n = "";//所需线程数
		int zoom = Integer.valueOf(ed_zoom);
		if(zoom<10){
			if((x4-x1)<=0.7||(y4-y1)<=0.7){
				n = "1";
				String zoom_op = "1-" + ed_zoom;
				thread_param_list.add(n);
				thread_param_list.add(zoom_op);
			}
		}
		
		
		return thread_param_list;
	}
	public static void main(String[] args) {
		String file_path1 = "D:/MAP_TEST/";
		DoSomething ds0 = new DoSomething("线程0_z=1---z=10", x1, x4, y1, y4, 1, 8,file_path1,"1");
		DoSomething ds1 = new DoSomething("线程1_z=11---z=12", x1, x4, y1, y4, 9, 10,file_path1,"1");
		DoSomething ds2 = new DoSomething("线程2_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path1,"1");
		DoSomething ds3 = new DoSomething("线程3_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path1,"1");
		DoSomething ds4 = new DoSomething("线程4_z=15", x1, x4, y1, y4, 15, 15,file_path1,"1");
		DoSomething ds5 = new DoSomething("线程4_z=16", x1, x4, y1, y4, 16, 16,file_path1,"1");
		DoSomething ds6 = new DoSomething("线程4_z=17", x1, x4, y1, y4, 17, 17,file_path1,"1");
		Thread t0 = new Thread(ds0);
		Thread t1 = new Thread(ds1);
		Thread t2 = new Thread(ds2);
		Thread t3 = new Thread(ds3);
		Thread t4 = new Thread(ds4);
		Thread t5 = new Thread(ds5);
		Thread t6 = new Thread(ds6);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
	}
}
