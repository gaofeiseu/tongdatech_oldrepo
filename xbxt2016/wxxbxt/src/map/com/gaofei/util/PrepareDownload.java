package com.gaofei.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.gaofei.bean.EnumTilesType;
import com.gaofei.bean.TaskBean;
import com.gaofei.bean.TilesBean;

public class PrepareDownload {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSZ");
	public static void run(List<TilesBean> list_tilesBean) throws Exception{
		for(TilesBean tb : list_tilesBean){
			downloadStep2(tb);
		}
	}
	private static void downloadStep2(TilesBean tb) throws Exception{
		for(int zoom = tb.getSt_zoom();zoom<=tb.getEd_zoom();zoom++){
			downloadStep3(tb, zoom);
		}
	}
	private static void downloadStep3(TilesBean tb,int zoom) throws Exception{
		Map<String,Integer> m = new HashMap<String,Integer>();
		m = MapUtils.getXtileYtile(tb.getSt_lon(), tb.getSt_lat(), tb.getEd_lon(), tb.getEd_lat(), zoom);
		int x1 = m.get("x1");
		int y1 = m.get("y1");
		int x4 = m.get("x4");
		int y4 = m.get("y4");
		ThreadPoolFactory.startUpThreadPool();
		for(EnumTilesType tile_type : tb.getList_map_type()){
			for(int x=x1;x<=x4;x++){
				for(int y=y1;y<=y4;y++){
					TaskBean taskBean = new TaskBean(x, y, zoom, tb.getSave_path(), tile_type);
					MapTask mt = new MapTask(taskBean);
					ThreadPoolFactory.addMapTaskIntoThreadPool(mt);
				}
			}
		}
		List<Future<Object>> list_future = ThreadPoolFactory.getListFuture();
		for (Future<Object> f : list_future) {
			try{
				if(f.get().toString().indexOf("ERROR")>-1){
					System.out.println("\t" + f.get().toString());
				}
				int now_value = AtomicCounter.increase();
				if(now_value%100==0){
					System.out.println("\tAlready download num:"+now_value+"\t\t"+format.format(new Date()));
				}
			}
			catch(Exception ex){
				System.err.println("@@@" + ex.getMessage());
			}
		}
		ThreadPoolFactory.shutDownThreadPool();
	}
}
