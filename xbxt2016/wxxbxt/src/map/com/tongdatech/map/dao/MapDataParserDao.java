package com.tongdatech.map.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;

import com.tongdatech.map.bean.MapDataParserBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.util.AntZip;
import com.tongdatech.map.util.ExcelWriteBean;
import com.tongdatech.map.util.ExcelWriter;
import com.tongdatech.map.util.PubFunc;
import com.tongdatech.map.util.TransformLatLng;
import com.tongdatech.sys.base.BaseDao;

public class MapDataParserDao extends BaseDao{

	public MapReturnBean uploadTXT(File[] upload, MapDataParserBean bean) throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
//		System.out.println("file_name:"+bean.getFile_name());
//		System.out.println("file_exe:"+bean.getFile_exe());
		@SuppressWarnings("unused")
		String file_name = bean.getFile_name();
		String file_exe = bean.getFile_exe();
		
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
		String filefolderPath = webrootPath+"/TxtSpace/";
		String uuidFilename = UUID.randomUUID().toString()+"."+file_exe;
		String savefilepath = filefolderPath+uuidFilename;
		
		try {
			if(upload[0].renameTo(new File(savefilepath))){
				//文件上传成功！
				//准备开始进行解析
				List<String> list_addr = new ArrayList<String>();
				try {
	                String encoding="GBK";
	                File file=new File(savefilepath);
	                if(file.isFile() && file.exists()){//判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        list_addr.add(lineTxt);
	                    }
	                    read.close();
	                }else{
	                	returnBean.setIf_success(false);
	    				returnBean.setMsg("找不到需要进行解析的TXT文件！");
	                }
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg("读取TXT文件内容出错！");
				}
				if(list_addr.size()==0){
					returnBean.setIf_success(false);
					returnBean.setMsg("TXT文件为空！");
				}else{
					Date date=new Date();
					List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
					for(int i=0;i<list_addr.size();i++){
						System.out.println(i+"   ---   "+list_addr.get(i)+"    ------  "+ date +"   ---   " +(new Date()));
						String resultStr = parseLatLngOnline(list_addr.get(i));
						
						Object o = JSONUtil.deserialize(resultStr);
						@SuppressWarnings("unchecked")
						Map<String,Map<String,Map<String,Object>>> rs = (Map<String, Map<String, Map<String, Object>>>) o;
						Map<String,Object> map = new HashMap<String,Object>();
						if(rs.get("result")==null||"".equals(rs.get("result"))){
							map.put("status", "ERROR");
						}else{
							try {
								map.put("lat", rs.get("result").get("location").get("lat"));
								map.put("lng", rs.get("result").get("location").get("lng"));
								map.put("status", "OK");
							} catch (Exception e) {
								map.put("status", "ERROR");
							}
							
						}
						list_map.add(map);
					}
					List<Map<String,Object>> list_result = new ArrayList<Map<String,Object>>();
					for(int i=0;i<list_map.size();i++){
						Map<String,Object> map = new HashMap<String,Object>();
						map = list_map.get(i);
						Map<String,Object> resultMap = new HashMap<String,Object>();
						if("ERROR".equals(map.get("status"))){
							resultMap.put("status", "ERROR");
						}else{
							resultMap.put("status", "OK");
							resultMap.put("lat", TransformLatLng.Convert_BD09_To_GCJ02(Double.parseDouble(String.valueOf(map.get("lat"))), Double.parseDouble(String.valueOf(map.get("lng")))).split("#")[0]);
							resultMap.put("lng", TransformLatLng.Convert_BD09_To_GCJ02(Double.parseDouble(String.valueOf(map.get("lat"))), Double.parseDouble(String.valueOf(map.get("lng")))).split("#")[1]);
						}
						list_result.add(resultMap);
					}
					//System.out.println(list_result);
					//list_addr			源地址
					//list_result		解析后的经纬度
					List<Map<String,Object>> list_map_success = new ArrayList<Map<String,Object>>();
					List<Map<String,Object>> list_map_error = new ArrayList<Map<String,Object>>();
					for(int i=0;i<list_result.size();i++){
						Map<String,Object> map = new HashMap<String,Object>();
						map = list_result.get(i);
						if("ERROR".equals(map.get("status"))){
							Map<String,Object> map_t = new HashMap<String,Object>();
							map_t.put("status", "ERROR");
							map_t.put("address", list_addr.get(i));
							map_t.put("lat", "未解析成功");
							map_t.put("lng", "未解析成功");
							list_map_error.add(map_t);
						}else if("OK".equals(map.get("status"))){
							Map<String,Object> map_t = new HashMap<String,Object>();
							map_t.put("status", "OK");
							map_t.put("address", list_addr.get(i));
							map_t.put("lat", map.get("lat"));
							map_t.put("lng", map.get("lng"));
							list_map_success.add(map_t);
						}
					}
					System.out.println("解析成功"+list_map_success.size()+"条数据，分别是："+list_map_success.toString());
					System.out.println("解析失败"+list_map_error.size()+"条数据，分别是："+list_map_error.toString());
					String exceloutputpath = webrootPath+"/ExcelOutputSpace/";
					String exceluuidfilename1 = UUID.randomUUID().toString()+"_success"+".xls";
					String exceluuidfilename2 = UUID.randomUUID().toString()+"_error"+".xls";
					ExcelWriteBean bean_success = new ExcelWriteBean();
					ExcelWriteBean bean_error = new ExcelWriteBean();
					
					bean_success.setFilepath(exceloutputpath);
					bean_success.setExcelFilename(exceluuidfilename1);
					bean_success.setSheetname("解析成功的数据");
					List<String> list_title_success = new ArrayList<String>();
					List<String> list_colname_success = new ArrayList<String>();
					list_title_success.add("解析成功的地址");
					list_title_success.add("纬度");
					list_title_success.add("经度");
					list_colname_success.add("address");
					list_colname_success.add("lat");
					list_colname_success.add("lng");
					bean_success.setList_colname(list_colname_success);
					bean_success.setList_title(list_title_success);
					bean_success.setList_rows(list_map_success);
					
					bean_error.setFilepath(exceloutputpath);
					bean_error.setExcelFilename(exceluuidfilename2);
					bean_error.setSheetname("解析失败的数据");
					List<String> list_title_error = new ArrayList<String>();
					List<String> list_colname_error = new ArrayList<String>();
					list_title_error.add("解析失败的地址");
					list_title_error.add("纬度");
					list_title_error.add("经度");
					list_colname_error.add("address");
					list_colname_error.add("lat");
					list_colname_error.add("lng");
					bean_error.setList_colname(list_colname_error);
					bean_error.setList_title(list_title_error);
					bean_error.setList_rows(list_map_error);
					
					boolean if_success1 = false;
					boolean if_success2 = false;
					ExcelWriter writer = new ExcelWriter();
					if_success1 = writer.writeExcel(bean_success);
					if_success2 = writer.writeExcel(bean_error);
					String zip_path = "";
					if(if_success1&&if_success2){
						boolean if_success3 = false;
						try {
							List<String> list_zipfile = new ArrayList<String>();
							list_zipfile.add(bean_success.getFilepath()+bean_success.getExcelFilename());
							list_zipfile.add(bean_error.getFilepath()+bean_error.getExcelFilename());
							String s1 = UUID.randomUUID().toString();
							String zip_name = exceloutputpath+s1+".zip";
							zip_path = "/ExcelOutputSpace/"+s1+".zip";
							AntZip.zipFile(list_zipfile, zip_name);
							if_success3 = true;
						} catch (Exception e) {
							e.printStackTrace();
							if_success3 = false;
						}
						if(if_success3){
							File file1 = new File(bean_success.getFilepath()+bean_success.getExcelFilename());
							File file2 = new File(bean_error.getFilepath()+bean_error.getExcelFilename());
							if(file1.delete()&&file2.delete()){
								returnBean.setIf_success(true);
								returnBean.setMsg("文件上传并解析成功！解析成功"+list_map_success.size()+"条数据"+"  解析失败"+list_map_error.size()+"条数据");
								returnBean.setReturnString(zip_path);
							}
						}else{
							returnBean.setIf_success(false);
							returnBean.setMsg("文件上传且解析成功，但是打包失败！");
						}
					}
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("文件上传失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	private String parseLatLngOnline(String str){
		String ss = "";
		try {
			System.out.println("2015年4月28日 15:33:33  parseLatLngOnline");
			String queryurl = "http://api.map.baidu.com/geocoder?address="+str+"&output=json";
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(queryurl);
			httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	    	httpGet.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
	    	httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
	    	httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
	    	httpGet.addHeader("Cache-Control", "max-age=0");
	    	httpGet.addHeader("Connection", "keep-alive");
	    	//httpGet.addHeader("Host", "api.map.baidu.com");
	    	httpGet.addHeader("UserAgent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1");
	    	RequestConfig config = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	    	httpGet.setConfig(config);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				String res_str = "";
				res_str = response.getStatusLine().toString();
				System.out.println("res_str------->"+res_str);
			    if(response.getStatusLine().toString().indexOf("200")>-1){
			    	HttpEntity entity = response.getEntity();
				    ss = EntityUtils.toString(entity);
				    EntityUtils.consume(entity);
			    }
			}
			catch (Exception e) {
				e.printStackTrace();
				ss = "ERROR";
			}
			finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ss = "ERROR";
		}
		return ss;
	}
	public static void main(String args[]) {  
        //创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
  
        HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?location=31.58024271787033,120.30188969791045&output=json");  
        System.out.println(httpGet.getRequestLine());  
        try {  
            //执行get请求  
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);  
            //获取响应消息实体  
            HttpEntity entity = httpResponse.getEntity();  
            //响应状态  
            System.out.println("status:" + httpResponse.getStatusLine());  
            //判断响应实体是否为空  
            if (entity != null) {  
            	   String json=EntityUtils.toString(entity);
                   Map map=PubFunc.json2Map(json);
            	  if(map!=null&&"OK".equals(map.get("status")))
                  {
                  	Map result=(Map)map.get("result");
                  	String addr=result.get("formatted_address")+"";
                   System.out.println(addr);
                  }
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
            	 //关闭流并释放资源  
                closeableHttpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
