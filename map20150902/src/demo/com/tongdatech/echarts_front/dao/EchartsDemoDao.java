package com.tongdatech.echarts_front.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.echarts_front.bean.EchartsDemoBean;
import com.tongdatech.echarts_front.bean.ReturnBean;
import com.tongdatech.sys.base.BaseDao;

public class EchartsDemoDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public ReturnBean loaddata(EchartsDemoBean bean) {
		ReturnBean returnBean = new ReturnBean();
		if("3".equals(bean.getEcharts_type())){
			boolean if_success = false;
			List<String> list_1 = new ArrayList<String>();//种类类型
			List<String> list_2 = new ArrayList<String>();//横坐标
			List<List<String>> list_list = new ArrayList<List<String>>();//数据List
			try {
				String sql1 = "select B from" +
						" (select count(*) as num,F_PARAMS('MARKER_TYPE',marker_class) as a,marker_class," +
						" F_PARAMS('USER_TYPE',post_class) as b,post_class from t_map_class_info" +
						" group by post_class,marker_class order by to_number(post_class),to_number(marker_class))" +
						" group by B,post_class order by to_number(post_class) ";
				List<Map<String,Object>> list_map_1 = new ArrayList<Map<String,Object>>();
				list_map_1 = db.query(sql1);
				for(int i=0;i<list_map_1.size();i++){
					list_1.add(String.valueOf(list_map_1.get(i).get("b")));
				}
				System.out.println("横坐标:"+list_1.toString());
				String sql2 = "select A from (select count(*),F_PARAMS('MARKER_TYPE',marker_class)" +
						" as a,marker_class,F_PARAMS('USER_TYPE',post_class) as b,post_class from" +
						" t_map_class_info group by post_class,marker_class order by to_number(post_class)," +
						" to_number(marker_class)) group by A,marker_class order by to_number(marker_class) ";
				List<Map<String,Object>> list_map_2 = new ArrayList<Map<String,Object>>();
				list_map_2 = db.query(sql2);
				for(int i=0;i<list_map_2.size();i++){
					list_2.add(String.valueOf(list_map_2.get(i).get("a")));
				}
				System.out.println("种类类型:"+list_2.toString());
				String sql3 = "select to_char(count(*)) as num,F_PARAMS('MARKER_TYPE',marker_class) as a," +
						"marker_class,F_PARAMS('USER_TYPE',post_class) as b,post_class from" +
						" t_map_class_info group by post_class,marker_class order by to_number(post_class),to_number(marker_class) ";
				List<Map<String,Object>> list_map_3 = new ArrayList<Map<String,Object>>();
				list_map_3 = db.query(sql3);
				for(int i=0;i<list_1.size();i++){
					List<String> list = new ArrayList<String>();
					for(int j=0;j<list_2.size();j++){
						list.add(String.valueOf(list_map_3.get(i*j).get("num")));
					}
					list_list.add(list);
				}
				System.out.println("数据List:"+list_list.toString());
				
				
				if_success = true;
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg("从数据库取出数据时报错！");
				if_success = false;
			}
			if(if_success){
				try {
					String returnStr = "";
					returnStr += "{";
					returnStr += "\"tooltip\":{\"trigger\":\"axis\"},";
					returnStr += "\"legend\":{\"data\":[";
					for(int i=0;i<list_1.size();i++){
						returnStr += "\""+list_1.get(i)+"\"";
						if(i!=(list_1.size()-1)){
							returnStr += ",";
						}
					}
					returnStr += "]},";
					returnStr += "\"toolbox\":{\"show\":true,\"orient\":\"vertical\"," +
							"\"feature\":{\"mark\":{\"show\":true},\"dataView\":{\"show\":true,\"readOnly\":false}," +
							"\"magicType\":{\"show\":true,\"type\":[\"line\",\"bar\"]},\"restore\":{\"show\":true}," +
							"\"saveAsImage\":{\"show\":true}}},";
					returnStr += "\"calculable\":true,\"xAxis\":[{\"type\":\"category\",";
					returnStr += "\"data\":[";
					for(int i=0;i<list_2.size();i++){
						returnStr += "\""+list_2.get(i)+"\"";
						if(i!=(list_2.size()-1)){
							returnStr += ",";
						}
					}
					returnStr += "]}],";
					returnStr += "\"yAxis\":[{\"type\":\"value\",\"splitArea\":{\"show\":true}}],";
					returnStr += "\"series\":[";
					for(int i=0;i<list_1.size();i++){
						List<String> list11 = new ArrayList<String>();
						list11 = list_list.get(i);
						returnStr += "{\"name\":";
						returnStr += "\""+list_1.get(i)+"\"";
						returnStr += ",\"type\":\"bar\",\"data\":[";
						for(int j=0;j<list_2.size();j++){
							returnStr += list11.get(j);
							if(j!=(list_2.size()-1)){
								returnStr += ",";
							}
						}
						returnStr += "]}";
						if(i!=(list_1.size()-1)){
							returnStr += ",";
						}
					}
					returnStr += "]}";
					System.out.println("returnStr--------------->"+returnStr);
					returnBean.setReturnString(returnStr);
					returnBean.setIf_success(true);
					returnBean.setMsg("成功！");
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg("进行数据组装的时候出现错误！");
				}
			}
		}
		return returnBean;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Map> zhanbi() throws Exception {
		String sql=

		" select "
+"机构名称   ,   "          
+"结算日期   ,   "          
+"年龄      ,    "          
+"人数      ,       "       
+"总人数     ,     "         
+"年龄段活期  ,     "        
+"年龄段总资产    , "         
+"活期       ,      "     
+"总资产      ,          " 
+"活期在总资产中占比   ,    "    
+"年龄段总资产在总资产在占比  , "
+"年龄段活期在总资产中占比  ,"
+"年龄段活期在总活期中占比  ,   "
+"年段活期在年龄段总资产中占比,"
+"年龄段人数占比             "
+"from zhanbi ";
		
		List<Map> list =db.query(sql);
	 return list;
	}
}
