package com.tongdatech.map.dao;

import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.bean.MapUpdateBean;
import com.tongdatech.map.download.DoSomething;
import com.tongdatech.sys.base.BaseDao;

public class MapUpdateDao extends BaseDao{

	public MapReturnBean dodownload(MapUpdateBean bean){
		MapReturnBean returnBean = new MapReturnBean();
		double x1 = Double.parseDouble(bean.getSt_lng());
		double x4 = Double.parseDouble(bean.getEd_lng());
		double y1 = Double.parseDouble(bean.getSt_lat());
		double y4 = Double.parseDouble(bean.getEd_lat());
		String map_type = bean.getMap_type();
		String file_path = bean.getDownload_url();
		try {
			int zoom = Integer.parseInt(bean.getMap_zoom());
			if(zoom==10){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				t0.start();
				t1.start();
				while(t0.isAlive()||t1.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==11){
				System.out.println("x1="+x1+" x4="+x4+" y1="+y1+" y4="+y4);
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=11", x1, x4, y1, y4, 11, 11,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				t0.start();
				t1.start();
				t2.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==12){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				t0.start();
				t1.start();
				t2.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==13){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=13", x1, x4, y1, y4, 13, 13,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==14){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==15){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				DoSomething ds4 = new DoSomething("线程5_z=15---z=15", x1, x4, y1, y4, 15, 15,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				Thread t4 = new Thread(ds4);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==16){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				DoSomething ds4 = new DoSomething("线程5_z=15---z=16", x1, x4, y1, y4, 15, 16,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				Thread t4 = new Thread(ds4);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==17){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				DoSomething ds4 = new DoSomething("线程5_z=15---z=16", x1, x4, y1, y4, 15, 16,file_path,map_type);
				DoSomething ds5 = new DoSomething("线程6_z=17---z=17", x1, x4, y1, y4, 17, 17,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				Thread t4 = new Thread(ds4);
				Thread t5 = new Thread(ds5);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				t5.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive()||t5.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==18){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				DoSomething ds4 = new DoSomething("线程5_z=15---z=16", x1, x4, y1, y4, 15, 16,file_path,map_type);
				DoSomething ds5 = new DoSomething("线程6_z=17---z=17", x1, x4, y1, y4, 17, 17,file_path,map_type);
				DoSomething ds6 = new DoSomething("线程7_z=18---z=18", x1, x4, y1, y4, 18, 18,file_path,map_type);
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
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive()||t5.isAlive()||t6.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else if(zoom==19){
				DoSomething ds0 = new DoSomething("线程1_z=1---z=9", x1, x4, y1, y4, 1, 9,file_path,map_type);
				DoSomething ds1 = new DoSomething("线程2_z=10---z=10", x1, x4, y1, y4, 10, 10,file_path,map_type);
				DoSomething ds2 = new DoSomething("线程3_z=11---z=12", x1, x4, y1, y4, 11, 12,file_path,map_type);
				DoSomething ds3 = new DoSomething("线程4_z=13---z=14", x1, x4, y1, y4, 13, 14,file_path,map_type);
				DoSomething ds4 = new DoSomething("线程5_z=15---z=16", x1, x4, y1, y4, 15, 16,file_path,map_type);
				DoSomething ds5 = new DoSomething("线程6_z=17---z=17", x1, x4, y1, y4, 17, 17,file_path,map_type);
				DoSomething ds6 = new DoSomething("线程7_z=18---z=18", x1, x4, y1, y4, 18, 18,file_path,map_type);
				DoSomething ds7 = new DoSomething("线程8_z=19---z=19", x1, x4, y1, y4, 19, 19,file_path,map_type);
				Thread t0 = new Thread(ds0);
				Thread t1 = new Thread(ds1);
				Thread t2 = new Thread(ds2);
				Thread t3 = new Thread(ds3);
				Thread t4 = new Thread(ds4);
				Thread t5 = new Thread(ds5);
				Thread t6 = new Thread(ds6);
				Thread t7 = new Thread(ds7);
				t0.start();
				t1.start();
				t2.start();
				t3.start();
				t4.start();
				t5.start();
				t6.start();
				t7.start();
				while(t0.isAlive()||t1.isAlive()||t2.isAlive()||t3.isAlive()||t4.isAlive()||t5.isAlive()||t6.isAlive()||t7.isAlive()){
					Thread.sleep(1000);
				}
				returnBean.setIf_success(true);
				returnBean.setMsg("下载完成！");
				return returnBean;
			}else{
				System.out.println("zoom-------------------->"+zoom);
				returnBean.setIf_success(false);
				returnBean.setMsg("地图层数超出系统限制，目前ZOOM="+zoom);
				return returnBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("下载过程中出错了！");
			return returnBean;
		}
	}
}
