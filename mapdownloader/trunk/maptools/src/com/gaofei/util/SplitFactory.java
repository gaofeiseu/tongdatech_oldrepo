package com.gaofei.util;

import java.util.ArrayList;
import java.util.List;

import com.gaofei.bean.BaseBean;
import com.gaofei.bean.TilesBean;
/**
 * 
 * @author gaofei
 *
 */
public class SplitFactory {
	/**
	 * split task to subtask
	 * @param startConfigBean
	 * @return
	 */
	public static List<TilesBean> handleConfigBean(BaseBean startConfigBean){
		List<TilesBean> list_tilesBean = new ArrayList<TilesBean>();
		try{
			if(startConfigBean!=null){
				if(startConfigBean.getEd_zoom()<=Params.start_split_zoom){
					//when zoom is small,no need to split to subtask
					list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), startConfigBean.getSt_zoom(), startConfigBean.getEd_zoom(), startConfigBean.getSt_lon(), startConfigBean.getEd_lon(), startConfigBean.getSt_lat(), startConfigBean.getEd_lat()));
				}else{
					list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), Params.start_zoom, Params.start_split_zoom, startConfigBean.getSt_lon(), startConfigBean.getEd_lon(), startConfigBean.getSt_lat(), startConfigBean.getEd_lat()));
					for(double i=startConfigBean.getSt_lon();i<=startConfigBean.getEd_lon();i += Params.per_lon){
						for(double j=startConfigBean.getSt_lat();j>=startConfigBean.getEd_lat();j -= Params.per_lat){
							if(((i+Params.per_lon)>=startConfigBean.getEd_lon())&&((j-Params.per_lat)<=startConfigBean.getEd_lat())){
								list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), (Params.start_split_zoom+1), startConfigBean.getEd_zoom(), i, startConfigBean.getEd_lon(), j, startConfigBean.getEd_lat()));
							}else if(((i+Params.per_lon)>=startConfigBean.getEd_lon())&&((j-Params.per_lat)>startConfigBean.getEd_lat())){
								list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), (Params.start_split_zoom+1), startConfigBean.getEd_zoom(), i, startConfigBean.getEd_lon(), j, (j-Params.per_lat)));
							}else if(((i+Params.per_lon)<startConfigBean.getEd_lon())&&((j-Params.per_lat)<=startConfigBean.getEd_lat())){
								list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), (Params.start_split_zoom+1), startConfigBean.getEd_zoom(), i, (i+Params.per_lon), j, startConfigBean.getEd_lat()));
							}else if(((i+Params.per_lon)<startConfigBean.getEd_lon())&&((j-Params.per_lat)>startConfigBean.getEd_lat())){
								list_tilesBean.add(new TilesBean(startConfigBean.getSave_path(), startConfigBean.getList_map_type(), (Params.start_split_zoom+1), startConfigBean.getEd_zoom(), i, (i+Params.per_lon), j, (j-Params.per_lat)));
							}
						}
					}
				}
			}else{
				throw new Exception ("初始化参数Bean为空！");
			}
		}
		catch(Exception ex){
			list_tilesBean = new ArrayList<TilesBean>();
			ex.printStackTrace();
		}
		return list_tilesBean;
	}
}
