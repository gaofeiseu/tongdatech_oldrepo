package com.tongdatech.map_poi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.json.JSONUtil;

import com.tongdatech.map_poi.bean.MapPoiBean;
import com.tongdatech.map_poi.bean.MapPoiReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.AjaxMsg;

public class MapPoiDao extends BaseDao{
	private static final String MY_KEY = "C5dae37ba0a97216555b8bf2b76bacfd";

	public MapPoiReturnBean getData(MapPoiBean bean) {
		MapPoiReturnBean returnBean = new MapPoiReturnBean();
		String query_str = bean.getQuery_str();
		String region_str = bean.getCity();
		int page_size = 20;
		int page_num = 0;
		boolean if_detail = false;
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		for(int i=page_num;i<200;i++){
			List<Map<String,Object>> list_map_t = new ArrayList<Map<String,Object>>();
			list_map_t = getDataFromUrl(query_str,page_size,i,if_detail,region_str);
			if(list_map_t==null||list_map_t.isEmpty()){
				break;
			}else{
				for(int j=0;j<list_map_t.size();j++){
					list_map.add(list_map_t.get(j));
				}
			}
		}
		if(list_map.size()>0){
			returnBean.setIf_success(true);
			returnBean.setList_map(list_map);
		}else{
			returnBean.setIf_success(false);
			returnBean.setMsg("没有对应的信息！");
		}
		
		return returnBean;
	}
	/**
	 * POI关键函数
	 * @param query_str		需要查询的特征字段
	 * @param page_size		每页展示多少个数据
	 * @param page_num		第几页了，从0开始
	 * @param if_detail		是否展示较为详细的信息，true为展示，false为不展示
	 * @param region_str	需要查询的城市名，例如南京
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String,Object>> getDataFromUrl(String query_str,int page_size,int page_num,boolean if_detail,String region_str){
		List<Map<String,Object>> return_list_map = new ArrayList<Map<String,Object>>();
		Map<String,Object> map_tmp = new HashMap<String,Object>();
		try {
			// http://api.map.baidu.com/place/v2/search?ak=&output=json&query=银行ATM&page_size=20&page_num=14&scope=1&region=南京
			String queryurl = "http://api.map.baidu.com/place/v2/search?ak="+MY_KEY+"&output=json&query="+query_str
					+"&page_size="+page_size+"&page_num="+page_num+"&scope="+((if_detail)?("2"):("1"))+"&region="+region_str;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(queryurl);
	    	RequestConfig config = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	    	httpGet.setConfig(config);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
			    if(response.getStatusLine().toString().indexOf("200")>-1){
			    	HttpEntity entity = response.getEntity();
			    	map_tmp = (Map<String, Object>) JSONUtil.deserialize(EntityUtils.toString(entity));
				    EntityUtils.consume(entity);
				    if("0".equals(String.valueOf(map_tmp.get("status")))&&"ok".equalsIgnoreCase(String.valueOf(map_tmp.get("message")))){
				    	List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
				    	list_map = (List<Map<String, Object>>) map_tmp.get("results");
				    	if(list_map.size()==0){
				    		
				    	}else{
				    		for(Map<String,Object> map:list_map){
				    			Map<String,Object> map_t = new HashMap<String,Object>();
				    			map_t.put("name", map.get("name"));
				    			map_t.put("lat", ((Map<String,Object>)(map.get("location"))).get("lat"));
				    			map_t.put("lng", ((Map<String,Object>)(map.get("location"))).get("lng"));
				    			map_t.put("address", map.get("address"));
				    			map_t.put("uid", map.get("uid"));
				    			return_list_map.add(map_t);
				    		}
				    	}
				    }else{
				    	System.out.println("----------------------------->status and message is error");
				    }
			    }
			}
			catch (Exception e) {
				e.printStackTrace();
				return_list_map = null;
			}
			finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return_list_map = null;
		}
		return return_list_map;
	}
	@SuppressWarnings("unchecked")
	public AjaxMsg query(MapPoiBean bean) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(bean.getPassword()==null||!"root123!@#".equals(bean.getPassword())){
				throw new Exception("查询密码不正确！");
			}
			String query_str = bean.getQuery_str();
			if(query_str!=null&&!"".equals(query_str)){
				List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
				String sql = "";
//				sql = "select * from ("+query_str+") where rownum<=50";
				sql = query_str;
				list_map = db.query(sql);
				am.setSuccess(true);
				am.setMsg("查询成功！");
				am.setBackParam(list_map);
			}else{
				throw new Exception("SQL语句不应该为空！");
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	public AjaxMsg runsql(MapPoiBean bean) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(bean.getPassword()==null||!"root123!@#".equals(bean.getPassword())){
				throw new Exception("查询密码不正确！");
			}
			String query_str = bean.getQuery_str();
			if(query_str!=null&&!"".equals(query_str)){
				db.runSql(query_str);
				am.setSuccess(true);
				am.setMsg("执行成功！");
			}else{
				throw new Exception("SQL语句不应该为空！");
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

}
