package com.tongdatech.map.download;

import java.util.ArrayList;
import java.util.List;

public class DoSomething implements Runnable{
	private double x1;
	private double x4;
	private double y1;
	private double y4;
	private int zoom_st;
	private int zoom_ed;
	private String name;
	private String file_path;
	private boolean if_success = false;
	private String map_type;
	public DoSomething(String name,double x1,double x4,double y1,double y4,int zoom_st,int zoom_ed,String filepath,String maptype){
		this.name = name;
		this.x1 = x1;
		this.x4 = x4;
		this.y1 = y1;
		this.y4 = y4;
		this.zoom_st = zoom_st;
		this.zoom_ed = zoom_ed;
		this.file_path = filepath;
		this.map_type = maptype;
	}
	public void run() {
		System.out.println("******************************************开始线程 "+name+" ******************************************");
		System.out.println("开始：系统时间："+LogInTxtFile.getNowTime());
		GetTileFromGivenParams gettile = new GetTileFromGivenParams(file_path);
		for(int k=zoom_st;k<zoom_ed+1;k++){
			List<Integer> resultlist = new ArrayList<Integer>();
			resultlist = TileFactory.getAllXtileYtile(x1, y1, x4, y4, k);
			int x_1 = resultlist.get(0);
			int y_1 = resultlist.get(1);
			int x_4 = resultlist.get(2);
			int y_4 = resultlist.get(3);
			for(int i=x_1;i<x_4+1;i++){
				for(int j=y_1;j<y_4+1;j++){
					try {
						gettile.run(i, j, k, map_type);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("线程 "+name+"（zoom="+k+"）结束 ******************************************");
		}
		System.out.println("##############线程 "+name+"全部结束 ##############");
		if_success = true;
	}
	public boolean if_success(){
		return if_success;
	}
}
