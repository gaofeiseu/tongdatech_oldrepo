package com.tongdatech.map.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tongdatech.map.bean.MapMarkersAdditionBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkersAdditionDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MapMarkersAdditionDao.class);

	@SuppressWarnings("unchecked")
	public MapReturnBean getUserTypeSelect(MapMarkersAdditionBean bean, UserInfo userinfo) throws Exception{
		System.out.println("userInfo----->"+userinfo);
		String user_type = "";
		user_type = userinfo.getUser_type();//2, 1
		user_type = user_type.trim();
		String[] user_type_arr = user_type.split(",");
		for (int i = 0; i < user_type_arr.length; i++) {
			user_type_arr[i] = user_type_arr[i].trim();
		}
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "select value,text from t_sys_param where type='USER_TYPE' and flag='1' order by value ";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		list_map = db.query(sql);
		if(list_map.size()>0){
			returnBean.setIf_success(true);
			String returnStr = "";
			returnStr += "<select id=\""+bean.getMap_select_name()+"\" style=\"width: 100px\" onchange=\"changeHtmlForBrchChange(this.value);\">";
			for(int i=0;i<list_map.size();i++){
				if(if_in_array(user_type_arr,String.valueOf(list_map.get(i).get("value")))){
					returnStr += "<option value =\""+list_map.get(i).get("value")+"\">"+list_map.get(i).get("text")+"</option>";
				}
			}
			returnStr += "</select>";
			returnBean.setReturnString(returnStr);
		}else{
			returnBean.setIf_success(false);
		}
		return returnBean;
	}
	private boolean if_in_array(String[] arr,String str){
		boolean if_success = false;
		for(int i=0;i<arr.length;i++){
			if(str.equals(arr[i])){
				if_success = true;
				break;
			}else{
				continue;
			}
		}
		return if_success;
	}
	/**
	 * 标注添加时，获取前台用户可以使用的子类型内容
	 * @param bean
	 * @param userinfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public MapReturnBean getChildClassSelect(MapMarkersAdditionBean bean,UserInfo userinfo) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
//		String sql = "select to_char(sn) as sn,class_name from t_map_class_info where brch_no='"+userinfo.getBrch_no()+"' and marker_class='"+bean.getMap_select_main_class_type()+"' and class_status='1' ";
		String sql = "select to_char(sn) as sn,class_name from t_map_class_info where marker_class='"+bean.getMap_select_main_class_type()+"' and class_status='1' ";//所有有标注权限的人，都能看到所有的可以标注的类型	2015年9月7日09:30:25 gf
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		list_map = db.query(sql);
		if(list_map.size()>0){
			returnBean.setIf_success(true);
			String returnStr = "";
			returnStr += "<select id='"+bean.getMap_select_childclass_id()+"' style='width: 100px'>";
			for(int i=0;i<list_map.size();i++){
				returnStr += "<option value ='"+list_map.get(i).get("sn")+"'>"+list_map.get(i).get("class_name")+"</option>";
			}
			returnStr += "</select>";
			returnBean.setReturnString(returnStr);
		}else{
			returnBean.setIf_success(false);
			String returnStr = "";
			returnStr += "<select id='"+bean.getMap_select_childclass_id()+"' style='width: 100px' >";
			
			returnStr += "</select>";
			returnBean.setReturnString(returnStr);
		}
		System.out.println("标注添加获取到的前台用户可用的子类型html=====>"+returnBean.getReturnString());
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public MapReturnBean getChildClassSelectOptions(MapMarkersAdditionBean bean,UserInfo userinfo) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "select to_char(sn) as sn,class_name from t_map_class_info where brch_no='"+userinfo.getBrch_no()+"' and marker_class='"+bean.getMap_select_1_mainclass()+"' and class_status='1' ";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		System.out.println("getChildClassSelectOptions----->sql---->"+sql);
		list_map = db.query(sql);
		if(list_map.size()>0){
			returnBean.setIf_success(true);
			List<String> list = new ArrayList<String>();
			for(int i=0;i<list_map.size();i++){
				String s_1 = "<option value='"+list_map.get(i).get("sn")+"'>"+list_map.get(i).get("class_name")+"</option>";
				list.add(s_1);
			}
			returnBean.setList(list);
		}else{
			returnBean.setIf_success(false);
		}
		return returnBean;
	}

	public MapReturnBean getMyIconSelectHtml(MapMarkersAdditionBean bean) {
		MapReturnBean returnBean = new MapReturnBean();
		try {
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/mapfiles/markers"+System.getProperty("file.separator");//图标文件夹的路径 示例： D:\MAP_JS\WebRoot\mapfiles/markers\
//			System.out.println("filefolderPath:"+filefolderPath);
			File file = new File(filefolderPath);
			String[] file_list = file.list();
//			System.out.println("图片文件夹中的所有文件是：");
//			for(int i=0;i<file_list.length;i++){
//				System.out.println(file_list[i]);
//			}
			String html_str = "";
			html_str += "<div style='height:200px;overflow:auto'><div>";
			html_str += "<font size='3'><b>修改标识图片&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b>";
			html_str += "</font><input type='button' id='bt_01' value='添加' onclick='open_icon_add_dlg();'></input><input type='button' id='bt_02' value='刷新' onclick='refresh_icon_select_dlg();'></input>";
			html_str += "</div><div><hr></div><table>";
			
			int total_img_num = 0;
			total_img_num = file_list.length;
			int rowNum = 0;
			int row_show_num = 9;//每行展示的图片是9个
			rowNum = (int)(total_img_num/row_show_num)+1;
			int lastrow_neednum = 0;
			lastrow_neednum = rowNum*row_show_num-file_list.length;
			for(int i=1;i<rowNum+1;i++){
				html_str+="<tr>";
				if(i==rowNum){
					//最后一行
					for(int j=(i-1)*row_show_num;j<file_list.length;j++){
						html_str+="<td><img src='"+"/mapfiles/markers/"+file_list[j]+"' onclick='setMarkerIconToMySelect(this.src);' /></td>";
					}
					for(int j=0;j<lastrow_neednum;j++){
						//对空余的td 补全
						html_str+="<td></td>";
					}
				}else{
					//不是最后一行
					for(int j=(i-1)*row_show_num;j<i*row_show_num;j++){
						html_str+="<td><img src='"+"/mapfiles/markers/"+file_list[j]+"' onclick='setMarkerIconToMySelect(this.src);' /></td>";
					}
				}
				html_str+="</tr>";
			}
			
			html_str += "</table></div>";
			returnBean.setReturnHtml(html_str);
			returnBean.setIf_success(true);
			returnBean.setMsg("Success");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("Error:45a21d1w121a2a");
		}
		return returnBean;
	}

	public MapReturnBean do_icon_add(File[] upload,MapMarkersAdditionBean bean) {
		MapReturnBean returnBean = new MapReturnBean();
		
		String add_icon_filename = "";
		add_icon_filename = bean.getAdd_icon_filename();
		add_icon_filename = add_icon_filename.substring(add_icon_filename.indexOf("fakepath")+9, add_icon_filename.length());
//		System.out.println("add_icon_filename:"+add_icon_filename);
		
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
		String filefolderPath = webrootPath+"/mapfiles/markers"+System.getProperty("file.separator");//图标文件夹的路径 示例： D:\MAP_JS\WebRoot\mapfiles/markers\
		File file = new File(filefolderPath);
		String[] file_list = file.list();
		
		BufferedImage bi = null;  
        try {  
            bi = ImageIO.read(upload[0]);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        int width = bi.getWidth(); // 像素  
        int height = bi.getHeight(); // 像素
        boolean if_success1=true;
        boolean if_success2=true;
        if(width>64){
        	if_success1=false;
        }
        if(height>64){
        	if_success2=false;
        }
        if(if_success1&&if_success2){
        	//验证通过
        	boolean if_success3 = true;
        	for(int i=0;i<file_list.length;i++){
        		if(add_icon_filename.equals(file_list[i])){
        			if_success3=false;
        			break;
        		}else{
        			continue;
        		}
        	}
        	if(!if_success3){
        		returnBean.setIf_success(false);
        		returnBean.setMsg("该文件名{"+add_icon_filename+"}已经被占用，如果图片确实没有上传过，请稍微修改文件名再尝试上传！");
        	}else{
        		if(upload[0].renameTo(new File(filefolderPath+add_icon_filename))){
        			returnBean.setIf_success(true);
        			returnBean.setMsg("图标新增成功！");
        		}else{
        			returnBean.setIf_success(false);
        			returnBean.setMsg("图标上传失败！");
        		}
        	}
        }else{
        	returnBean.setIf_success(false);
        	returnBean.setMsg("图片格式太大，图标只接受最大64*64像素的图片！");
        }
        return returnBean;
	}

	@SuppressWarnings("unchecked")
	public MapReturnBean giveHiddenDivContent(MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "";
		String sql1 = "";
		
		sql = "SELECT a.class_columnname AS columnname,"+
			"a.class_comments        AS class_comments,"+
			"a.class_columntype      AS class_columntype,"+
			"a.class_nullable        AS class_nullable,"+
			"a.class_columnsize      AS class_columnsize"+
			" FROM t_map_class_status a,"+
			"t_map_class_info b"+
			" WHERE a.class_tablename =b.class_table_name"+
			" AND b.marker_class      ='1'"+
//			" AND b.brch_no        ='"+userinfo.getBrch_no()+"'"+
			" AND b.sn                ='"+bean.getHtml_addpoint_select3()+"'"+
			" AND b.class_status      ='1'"+
			" AND a.COLUMN_TYPE      ='2'"+
			" AND a.class_columnstatus='1' order by a.sn ";
		System.out.println("sql-------------->"+sql);
		sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getHtml_addpoint_select3()+"'";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String table_name = "";
		try {
			list = db.query(sql);
			table_name = db.queryString(sql1);
			if(list.size()>0){
				returnBean.setIf_success(true);
				returnBean.setList_map(list);
				returnBean.setReturnString(table_name);
			}else{
				returnBean.setIf_success(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 点标注添加的实现函数
	 * @param upload	如果点标注添加中含有上传的图片文件，那么这个对象就是
	 * @param bean		点标注参数的包含javabean
	 * @return
	 */
	public MapReturnBean addPointMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		
		String id_str = bean.getHtml_id_str();
		String value_str = bean.getHtml_value_str();
		String table_name = bean.getHtml_table_name();//该子类的table_name 用于进行插入操作
		String icon_num = bean.getHtml_icon_num();//本次操作的标注图标的url
		String lat = bean.getHtml_location_lat();//本次操作的lat
		String lon = bean.getHtml_location_lon();//本次操作的lon
		//所有关于标注的颜色，图标序号现在都插入t_map_class_marker
		//t_map_class_marker中c_type是标注类型：1-点 2-线 3-面
		//如果是点，那就将icon_num 插入marker1
		//如果是线，那就将线的颜色插入marker1，线的起点的icon_num插入marker2，线的终点的icon_num插入marker3
		//如果是面，那就将面的颜色插入marker1
		
		System.out.println("html_id_str:"+id_str);
		System.out.println("html_value_str:"+value_str);
		System.out.println("html_table_name:"+bean.getHtml_table_name());
		System.out.println("html_icon_num:"+bean.getHtml_icon_num());
		System.out.println("html_location_lat:"+bean.getHtml_location_lat());
		System.out.println("html_location_lon:"+bean.getHtml_location_lon());
		value_str = goodStr(value_str);//将字符串中的被html编码转化过的字符转化回来，比如将&quot;  转化为双引号，这样才不会报错
		id_str = goodStr(id_str);
		
		Gson gson = new Gson();
		List<String> value_list = gson.fromJson(value_str, new TypeToken<List<String>>(){}.getType());//值的list
		List<String> id_list = gson.fromJson(id_str, new TypeToken<List<String>>(){}.getType());//列名的list
		System.out.println("value_list:"+value_list.toString());
		System.out.println("id_list:"+id_list.toString());
		
		
		int file_id_num = -1;
		@SuppressWarnings("unused")
		String file_name = "";
		boolean if_upload_file = false;
		boolean if_upload_success = false;
		String upload_img_path = "";//上传到服务器的图片的URL
		String img_exe = "";
		if(upload==null){
			if_upload_file = false;
		}else{
			if_upload_file = true;
			for(int i=0;i<value_list.size();i++){
				if(value_list.get(i).indexOf(".jpg")>-1||value_list.get(i).indexOf(".JPG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpg";
					break;
				}
				else if(value_list.get(i).indexOf(".png")>-1||value_list.get(i).indexOf(".PNG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".png";
					break;
				}
				else if(value_list.get(i).indexOf(".bmp")>-1||value_list.get(i).indexOf(".BMP")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".bmp";
					break;
				}
				else if(value_list.get(i).indexOf(".gif")>-1||value_list.get(i).indexOf(".GIF")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".gif";
					break;
				}
				else if(value_list.get(i).indexOf(".jpeg")>-1||value_list.get(i).indexOf(".JPEG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpeg";
					break;
				}
				else{
					continue;
				}
			}
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
			String uuidFilename = UUID.randomUUID().toString()+img_exe;
			String filepath = filefolderPath+uuidFilename;
			System.out.println("filepath:"+filepath);
			if(upload[0].renameTo(new File(filepath))){
				System.out.println("上传成功！");
				System.out.println("file_id_num:"+file_id_num);
				upload_img_path = "/MarkerImage/"+uuidFilename;
				System.out.println("upload_img_path--------------->"+upload_img_path);//TODO
				List<String> value =new LinkedList<String> ();
				value.add(upload_img_path);
				value_list.addAll(file_id_num, value);
				value_list.remove(file_id_num+1);
				if_upload_success = true;
			}else{
				System.out.println("上传失败！");
				if_upload_success = false;
			}
		}
		
		if(if_upload_file){
			//带有图片
			if(if_upload_success){
				//进行数据库插入
				String sql = "";
				sql = "select SEQ_"+table_name+".nextval from dual";
				String sn = "";
				String post_type = "";
				List<String> column_type_list = new ArrayList<String>();//记录的是数据类型，需要根据数据类型进行插入SQL的个性化处理
				try {
					sn = db.queryString(sql);//获取子类型表的可用sn
				//	sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
				//	post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
					sql = "select SEQ_T_map_basic.nextval from dual";
					String basic_sn=db.queryString(sql);//basic 表中sn
					String show_text="";

					for(int i=0;i<id_list.size();i++){
						sql = "select class_columntype from t_map_class_status where column_type='2' and class_tablename='"+table_name+"' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
						String column_type = "";
						column_type = db.queryString(sql);
						column_type_list.add(column_type);
					}
					try {
						//开始进行子类型表的数据插入
						String insertSQL = "";
					//	insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
						insertSQL += "insert into "+table_name+" (sn,";
						for(int i=0;i<id_list.size();i++){
							if(i!=(id_list.size()-1)){
								insertSQL+=id_list.get(i)+",";
							}else{
								insertSQL+=id_list.get(i);
							}
						}
						insertSQL+=") values ("+sn+",";
						for(int i=0;i<id_list.size();i++){
							if(i==0)//20150112，第一个字段为显示字段
							{
								show_text=value_list.get(i);
							}
							if(i!=(id_list.size()-1)){
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"',";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL,";
									}else{
										insertSQL+=value_list.get(i)+",";
									}
								}
							}else{
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"')";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL)";
									}else{
										insertSQL+=value_list.get(i)+")";
									}
								}
							}
						}
						
						db.connect();
						db.startTransaction();
						
						int insertNum = db.insert(insertSQL);
						String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
						"'"+basic_sn+"','"+lat+"','"+lon+"','"+icon_num+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','1','"+userinfo.getBrch_no()+"')";
						
						int basic_num=db.insert(basic_sql);
						if(insertNum==1&&basic_num==1){
							List<String> list_comments = new ArrayList<String>();
							for(int i=0;i<id_list.size();i++){
								String sql4 = "";
								sql4 = "select class_comments from t_map_class_status where column_type='2' and class_tablename='"+table_name+"' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
								String comments_1 = "";
								comments_1 = db.queryString(sql4);//查询出前台需要展示的字段的名称，每个字段属性的展示名称都存在comment字段中
								if(!"".equals(comments_1)&&comments_1!=null){
									list_comments.add(comments_1);
								}
							}
							String returnString = "";
							returnString+="已保存！\n";
							for(int i=0;i<value_list.size();i++){
								returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
							}
							returnBean.setReturnString(returnString);
							returnBean.setIf_success(true);
							returnBean.setMsg("添加成功！");
							db.commit();
						}else {
							returnBean.setIf_success(false);
							returnBean.setMsg("错误：A78F4W5A11！");
							db.rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
						db.rollback();
					}
					finally{
						db.endTransaction();
						db.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("图片上传失败！");
			}
		}else{
			//不带有图片
			String sql = "";
			sql = "select SEQ_"+table_name+".nextval from dual";
			String sn = "";
			String post_type = "";
			List<String> column_type_list = new ArrayList<String>();
			try {
				sn = db.queryString(sql);//获取子类型表的可用sn
//				sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//				post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
				sql = "select SEQ_T_map_basic.nextval from dual";
				String basic_sn=db.queryString(sql);//basic 表中sn
				String show_text="";
				
				for(int i=0;i<id_list.size();i++){
					sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and COLUMN_TYPE='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
					String column_type = "";
					column_type = db.queryString(sql);
					column_type_list.add(column_type);
				}
				try {
					//开始进行子类型表的数据插入
					String insertSQL = "";
//					insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
					insertSQL += "insert into "+table_name+" (sn,";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							insertSQL+=id_list.get(i)+",";
						}else{
							insertSQL+=id_list.get(i);
						}
					}
//					insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+icon_num+"',";
					insertSQL+=") values ("+sn+",";
					for(int i=0;i<id_list.size();i++){
						if(i==0)//20150112，第一个字段为显示字段
						{
							show_text=value_list.get(i);
						}
						if(i!=(id_list.size()-1)){
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"',";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL,";
								}else{
									insertSQL+=value_list.get(i)+",";
								}
							}
						}else{
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"')";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL)";
								}else{
									insertSQL+=value_list.get(i)+")";
								}
							}
						}
					}
//					System.out.println("insertSQL="+insertSQL);
					
					db.connect();
					db.startTransaction();
					
					int insertNum = db.insert(insertSQL);
					String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
					
					"'"+basic_sn+"','"+lat+"','"+lon+"','"+icon_num+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','1','"+userinfo.getBrch_no()+"')";
					
					int basic_num=db.insert(basic_sql);
					if(insertNum==1&&basic_num==1){
				
						List<String> list_comments = new ArrayList<String>();
						for(int i=0;i<id_list.size();i++){
							String sql4 = "";
							sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and COLUMN_TYPE='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
							String comments_1 = "";
							comments_1 = db.queryString(sql4);
							if(!"".equals(comments_1)&&comments_1!=null){
								list_comments.add(comments_1);
							}
						}
						String returnString = "";
						returnString+="已保存！\n";
						for(int i=0;i<value_list.size();i++){
							returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
						}
						returnBean.setReturnString(returnString);
						returnBean.setIf_success(true);
						returnBean.setMsg("添加成功！");
						db.commit();
					}else {
						returnBean.setIf_success(false);
						returnBean.setMsg("错误：A78F4W5A11！");
						db.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		}
		return returnBean;
	}
	/**
	 * 由于漏洞修补，会将前台传递的参数中的特殊字符进行Html encode，这会导致后台数据解析时发生问题，可以通过此函数转化回来
	 * @param s
	 * @return
	 */
	private String goodStr(String s){
		if(s!=null&&!"".equals(s)){
			String a = "";
			a = s.replaceAll("&quot;", "\"").replaceAll("&gt;", ">");
			//可以在这里添加新的规则
			return a;
		}else{
			return "";
		}
	}
	@SuppressWarnings("unchecked")
	public MapReturnBean giveHiddenDivContent2(MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "";
		String sql1 = "";
		
		sql = "SELECT a.class_columnname AS columnname,"+
			"a.class_comments        AS class_comments,"+
			"a.class_columntype      AS class_columntype,"+
			"a.class_nullable        AS class_nullable,"+
			"a.class_columnsize      AS class_columnsize"+
			" FROM t_map_class_status a,"+
			"t_map_class_info b"+
			" WHERE a.class_tablename =b.class_table_name"+
			" AND b.marker_class      ='"+bean.getHtml_addline_select1()+"'"+
//			" AND b.brch_no        ='"+userinfo.getBrch_no()+"'"+
			" AND b.sn                ='"+bean.getHtml_addline_select3()+"'"+
			" AND b.class_status      ='1'"+
			" AND a.column_type      ='2'"+
			" AND a.class_columnstatus='1' order by a.sn ";
		sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getHtml_addline_select3()+"'";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String table_name = "";
		try {
			list = db.query(sql);
			table_name = db.queryString(sql1);
			if(list.size()>0){
				returnBean.setIf_success(true);
				returnBean.setList_map(list);
				returnBean.setReturnString(table_name);
			}else{
				returnBean.setIf_success(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 添加线标注
	 * @param upload
	 * @param bean
	 * @return
	 */
	public MapReturnBean addLineMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		Gson gson = new Gson();
		
		String id_str = goodStr(bean.getHtml_id_str());
		String value_str = goodStr(bean.getHtml_value_str());
		
		
		List<String> value_list = gson.fromJson(value_str, new TypeToken<List<String>>(){}.getType());
		List<String> id_list = gson.fromJson(id_str, new TypeToken<List<String>>(){}.getType());
		String table_name = bean.getHtml_table_name();
		String line_color = bean.getHtml_icon_num();
		line_color = line_color.replace("@", "#");
		String lat = bean.getHtml_location_lat();
		String lon = bean.getHtml_location_lon();
		lat = lat.replace(" ", "");
		lon = lon.replace(" ", "");
		lat = lat.replace("undefined", "");
		lon = lon.replace("undefined", "");
		lat = lat.replace("null", "");
		lon = lon.replace("null", "");
		lat = lat.replace("@", "#");
		lon = lon.replace("@", "#");
		System.out.println("lat:"+lat);
		System.out.println("lon:"+lon);
		
		int file_id_num = -1;
		@SuppressWarnings("unused")
		String file_name = "";
		boolean if_upload_file = false;
		boolean if_upload_success = false;
		String upload_img_path = "";//上传到服务器的图片的URL
		String img_exe = "";
		if(upload==null){
			if_upload_file = false;
		}else{
			if_upload_file = true;
			for(int i=0;i<value_list.size();i++){
				if(value_list.get(i).indexOf(".jpg")>-1||value_list.get(i).indexOf(".JPG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpg";
					break;
				}
				else if(value_list.get(i).indexOf(".png")>-1||value_list.get(i).indexOf(".PNG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".png";
					break;
				}
				else if(value_list.get(i).indexOf(".bmp")>-1||value_list.get(i).indexOf(".BMP")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".bmp";
					break;
				}
				else if(value_list.get(i).indexOf(".gif")>-1||value_list.get(i).indexOf(".GIF")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".gif";
					break;
				}
				else if(value_list.get(i).indexOf(".jpeg")>-1||value_list.get(i).indexOf(".JPEG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpeg";
					break;
				}
				else{
					continue;
				}
			}
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
			String uuidFilename = UUID.randomUUID().toString()+img_exe;
			String filepath = filefolderPath+uuidFilename;
			System.out.println("filepath:"+filepath);
			if(upload[0].renameTo(new File(filepath))){
				System.out.println("上传成功！");
				System.out.println("file_id_num:"+file_id_num);
				upload_img_path = "/MarkerImage/"+uuidFilename;
				System.out.println("upload_img_path---------------->"+upload_img_path);//TODO
				if_upload_success = true;
			}else{
				System.out.println("上传失败！");
				if_upload_success = false;
			}
		}
		
		if(if_upload_file){
			//带有图片
			if(if_upload_success){
				String sql = "";
				sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
				String sn = "";
				String post_type = "";
				List<String> column_type_list = new ArrayList<String>();
				try {
					sn = db.queryString(sql);//获取子类型表的可用sn
//					sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//					post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
					
					sql = "select SEQ_T_map_basic.nextval from dual";
					String basic_sn=db.queryString(sql);//basic 表中sn
					String show_text="";
					for(int i=0;i<id_list.size();i++){
						sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
						String column_type = "";
						column_type = db.queryString(sql);
						column_type_list.add(column_type);
					}
					try {
						//开始进行子类型表的数据插入
						String insertSQL = "";
//						insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
						insertSQL += "insert into "+table_name+" (sn,";
						for(int i=0;i<id_list.size();i++){
							if(i!=(id_list.size()-1)){
								insertSQL+=id_list.get(i)+",";
							}else{
								insertSQL+=id_list.get(i);
							}
						}
//						insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+line_color+"',";
						insertSQL+=") values ("+sn+",";
						for(int i=0;i<id_list.size();i++){
							if(i==0)//20150112，第一个字段为显示字段
							{
								show_text=value_list.get(i);
							}
							if(i!=(id_list.size()-1)){
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"',";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL,";
									}else{
										insertSQL+=value_list.get(i)+",";
									}
								}
							}else{
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"')";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL)";
									}else{
										insertSQL+=value_list.get(i)+")";
									}
								}
							}
						}
//						System.out.println("insertSQL="+insertSQL);
						
						db.connect();
						db.startTransaction();
						
						int insertNum = db.insert(insertSQL);
						String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
						
						"'"+basic_sn+"','"+lat+"','"+lon+"','"+line_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','2','"+userinfo.getBrch_no()+"')";
						
						int basic_num=db.insert(basic_sql);
						if(insertNum==1&&basic_num==1){
							List<String> list_comments = new ArrayList<String>();
							for(int i=0;i<id_list.size();i++){
								String sql4 = "";
								sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
								String comments_1 = "";
								comments_1 = db.queryString(sql4);
								if(!"".equals(comments_1)&&comments_1!=null){
									list_comments.add(comments_1);
								}
							}
							String returnString = "";
							returnString+="已保存！\n";
							for(int i=0;i<value_list.size();i++){
								returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
							}
							returnBean.setReturnString(returnString);
							returnBean.setIf_success(true);
							returnBean.setMsg("添加成功！");
							db.commit();
						}else {
							returnBean.setIf_success(false);
							returnBean.setMsg("错误：A78F4W5A11！");
							db.rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
						db.rollback();
					}
					finally{
						db.endTransaction();
						db.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("图片上传失败！");
			}
		}else{
			//不带有图片
			String sql = "";
			sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
			String sn = "";
			String post_type = "";
			List<String> column_type_list = new ArrayList<String>();
			try {
				sn = db.queryString(sql);//获取子类型表的可用sn
//				sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//				post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
				
				sql = "select SEQ_T_map_basic.nextval from dual";
				String basic_sn=db.queryString(sql);//basic 表中sn
				String show_text="";
				for(int i=0;i<id_list.size();i++){
					sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
					String column_type = "";
					column_type = db.queryString(sql);
					column_type_list.add(column_type);
				}
				try {
					//开始进行子类型表的数据插入
					String insertSQL = "";
//					insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
					insertSQL += "insert into "+table_name+" (sn,";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							insertSQL+=id_list.get(i)+",";
						}else{
							insertSQL+=id_list.get(i);
						}
					}
//					insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+line_color+"',";
					insertSQL+=") values ("+sn+",";
					for(int i=0;i<id_list.size();i++){
						if(i==0)//20150112，第一个字段为显示字段
						{
							show_text=value_list.get(i);
						}
						if(i!=(id_list.size()-1)){
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"',";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL,";
								}else{
									insertSQL+=value_list.get(i)+",";
								}
							}
						}else{
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"')";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL)";
								}else{
									insertSQL+=value_list.get(i)+")";
								}
							}
						}
					}
//					System.out.println("insertSQL="+insertSQL);
					
					db.connect();
					db.startTransaction();
					
					int insertNum = db.insert(insertSQL);
					String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
					"'"+basic_sn+"','"+lat+"','"+lon+"','"+line_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','2','"+userinfo.getBrch_no()+"')";
					
					int basic_num=db.insert(basic_sql);
					if(insertNum==1&&basic_num==1){
						List<String> list_comments = new ArrayList<String>();
						for(int i=0;i<id_list.size();i++){
							String sql4 = "";
							sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
							String comments_1 = "";
							comments_1 = db.queryString(sql4);
							if(!"".equals(comments_1)&&comments_1!=null){
								list_comments.add(comments_1);
							}
						}
						String returnString = "";
						returnString+="已保存！\n";
						for(int i=0;i<value_list.size();i++){
							returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
						}
						returnBean.setReturnString(returnString);
						returnBean.setIf_success(true);
						returnBean.setMsg("添加成功！");
						db.commit();
					}else {
						returnBean.setIf_success(false);
						returnBean.setMsg("错误：A78F4W5A11！");
						db.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		}
		
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public MapReturnBean giveHiddenDivContent3(MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "";
		String sql1 = "";
		
		sql = "SELECT a.class_columnname AS columnname,"+
			"a.class_comments        AS class_comments,"+
			"a.class_columntype      AS class_columntype,"+
			"a.class_nullable        AS class_nullable,"+
			"a.class_columnsize      AS class_columnsize"+
			" FROM t_map_class_status a,"+
			"t_map_class_info b"+
			" WHERE a.class_tablename =b.class_table_name"+
			" AND b.marker_class      ='"+bean.getHtml_addarea_select1()+"'"+
//			" AND b.brch_no        ='"+userinfo.getBrch_no()+"'"+
			" AND b.sn                ='"+bean.getHtml_addarea_select3()+"'"+
			" AND b.class_status      ='1'"+
			" AND a.column_type      ='2'"+
			" AND a.class_columnstatus='1' order by a.sn ";
		sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getHtml_addarea_select3()+"'";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String table_name = "";
		try {
			list = db.query(sql);
			table_name = db.queryString(sql1);
			if(list.size()>0){
				returnBean.setIf_success(true);
				returnBean.setList_map(list);
				returnBean.setReturnString(table_name);
			}else{
				returnBean.setIf_success(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 添加面标注
	 * @param upload
	 * @param bean
	 * @return
	 */
	public MapReturnBean addAreaMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) {
		
//		bean.setHtml_id_str(goodStr(bean.getHtml_id_str()));
//		bean.setHtml_value_str(goodStr(bean.getHtml_value_str()));
		String id_str = goodStr(bean.getHtml_id_str());
		String value_str = goodStr(bean.getHtml_value_str());
		
		System.out.println("id:"+id_str);
		System.out.println("value:"+value_str);
		System.out.println("table_name:"+bean.getHtml_table_name());
		System.out.println("line_color:"+bean.getHtml_icon_num());
		System.out.println("location_lat:"+bean.getHtml_location_lat());
		System.out.println("location_lon:"+bean.getHtml_location_lon());
		
		MapReturnBean returnBean = new MapReturnBean();
		Gson gson = new Gson();
		
		
		List<String> value_list = gson.fromJson(value_str, new TypeToken<List<String>>(){}.getType());//值的list
		List<String> id_list = gson.fromJson(id_str, new TypeToken<List<String>>(){}.getType());//列名的list
		String table_name = bean.getHtml_table_name();//该子类的table_name 用于进行插入操作
		String area_color = bean.getHtml_icon_num();//本次操作的线的颜色代码
		area_color = area_color.replace("@", "#");
		String lat = bean.getHtml_location_lat();//本次操作的lat
		String lon = bean.getHtml_location_lon();//本次操作的lon
		lat = lat.replace(" ", "");
		lon = lon.replace(" ", "");
		lat = lat.replace("null", "");
		lon = lon.replace("null", "");
		lat = lat.replace("undefined", "");
		lon = lon.replace("undefined", "");
		lat = lat.replace("@", "#");
		lon = lon.replace("@", "#");
		System.out.println("lat:"+lat);
		System.out.println("lon:"+lon);
		
		int file_id_num = -1;
		@SuppressWarnings("unused")
		String file_name = "";
		boolean if_upload_file = false;
		boolean if_upload_success = false;
		String upload_img_path = "";//上传到服务器的图片的URL
		String img_exe = "";
		if(upload==null){
			if_upload_file = false;
		}else{
			if_upload_file = true;
			for(int i=0;i<value_list.size();i++){
				if(value_list.get(i).indexOf(".jpg")>-1||value_list.get(i).indexOf(".JPG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpg";
					break;
				}
				else if(value_list.get(i).indexOf(".png")>-1||value_list.get(i).indexOf(".PNG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".png";
					break;
				}
				else if(value_list.get(i).indexOf(".bmp")>-1||value_list.get(i).indexOf(".BMP")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".bmp";
					break;
				}
				else if(value_list.get(i).indexOf(".gif")>-1||value_list.get(i).indexOf(".GIF")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".gif";
					break;
				}
				else if(value_list.get(i).indexOf(".jpeg")>-1||value_list.get(i).indexOf(".JPEG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpeg";
					break;
				}
				else{
					continue;
				}
			}
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
			String uuidFilename = UUID.randomUUID().toString()+img_exe;
			String filepath = filefolderPath+uuidFilename;
			System.out.println("filepath:"+filepath);
			if(upload[0].renameTo(new File(filepath))){
				System.out.println("上传成功！");
				System.out.println("file_id_num:"+file_id_num);
				upload_img_path = "/MarkerImage/"+uuidFilename;
				System.out.println("upload_img_path--------->"+upload_img_path);//TODO
				if_upload_success = true;
			}else{
				System.out.println("上传失败！");
				if_upload_success = false;
			}
		}
		
		if(if_upload_file){
			//带有图片
			if(if_upload_success){
				String sql = "";
				sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
				String sn = "";
				String post_type = "";
				List<String> column_type_list = new ArrayList<String>();
				try {
					sn = db.queryString(sql);//获取子类型表的可用sn
//					sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//					post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
					sql = "select SEQ_T_map_basic.nextval from dual";
					String basic_sn=db.queryString(sql);//basic 表中sn
					String show_text="";
					
					for(int i=0;i<id_list.size();i++){
						sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
						String column_type = "";
						column_type = db.queryString(sql);
						column_type_list.add(column_type);
					}
					try {
						//开始进行子类型表的数据插入
						String insertSQL = "";
//						insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
						insertSQL += "insert into "+table_name+" (sn,";
						for(int i=0;i<id_list.size();i++){
							if(i!=(id_list.size()-1)){
								insertSQL+=id_list.get(i)+",";
							}else{
								insertSQL+=id_list.get(i);
							}
						}
//						insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"',";
						insertSQL+=") values ("+sn+",";
						for(int i=0;i<id_list.size();i++){
							if(i==0)//20150112，第一个字段为显示字段
							{
								show_text=value_list.get(i);
							}
							if(i!=(id_list.size()-1)){
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"',";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL,";
									}else{
										insertSQL+=value_list.get(i)+",";
									}
								}
							}else{
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"')";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL)";
									}else{
										insertSQL+=value_list.get(i)+")";
									}
								}
							}
						}
						
						db.connect();
						db.startTransaction();
						
						int insertNum = db.insert(insertSQL);
						String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
						
						"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','3','"+userinfo.getBrch_no()+"')";
						
						int basic_num=db.insert(basic_sql);
						if(insertNum==1&&basic_num==1){
							List<String> list_comments = new ArrayList<String>();
							for(int i=0;i<id_list.size();i++){
								String sql4 = "";
								sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
								String comments_1 = "";
								comments_1 = db.queryString(sql4);
								if(!"".equals(comments_1)&&comments_1!=null){
									list_comments.add(comments_1);
								}
							}
							String returnString = "";
							returnString+="已保存！\n";
							for(int i=0;i<value_list.size();i++){
								returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
							}
							returnBean.setReturnString(returnString);
							returnBean.setIf_success(true);
							returnBean.setMsg("添加成功！");
							db.commit();
						}else {
							returnBean.setIf_success(false);
							returnBean.setMsg("错误：A78F4W5A11！");
							db.rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
						db.rollback();
					}
					finally{
						db.endTransaction();
						db.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("图片上传失败！");
			}
		}else{
			String sql = "";
			sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
			String sn = "";
			String post_type = "";
			List<String> column_type_list = new ArrayList<String>();
			try {
				sn = db.queryString(sql);//获取子类型表的可用sn
//				sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//				post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
				
				sql = "select SEQ_T_map_basic.nextval from dual";
				String basic_sn=db.queryString(sql);//basic 表中sn
				String show_text="";
				for(int i=0;i<id_list.size();i++){
					sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
					String column_type = "";
					column_type = db.queryString(sql);
					column_type_list.add(column_type);
				}
				try {
					//开始进行子类型表的数据插入
					String insertSQL = "";
//					insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,";
					insertSQL += "insert into "+table_name+" (sn,";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							insertSQL+=id_list.get(i)+",";
						}else{
							insertSQL+=id_list.get(i);
						}
					}
//					insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"',";
					insertSQL+=") values ("+sn+",";
					for(int i=0;i<id_list.size();i++){
						if(i==0)//20150112，第一个字段为显示字段
						{
							show_text=value_list.get(i);
						}
						if(i!=(id_list.size()-1)){
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"',";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL,";
								}else{
									insertSQL+=value_list.get(i)+",";
								}
							}
						}else{
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"')";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL)";
								}else{
									insertSQL+=value_list.get(i)+")";
								}
							}
						}
					}
					
					db.connect();
					db.startTransaction();
					System.out.println("insertSQL-------------->"+insertSQL);
					int insertNum = db.insert(insertSQL);
					String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,marker_type,op_brch) values("+
					"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','3','"+userinfo.getBrch_no()+"')";
					
					int basic_num=db.insert(basic_sql);
					if(insertNum==1&&basic_num==1){
						List<String> list_comments = new ArrayList<String>();
						for(int i=0;i<id_list.size();i++){
							String sql4 = "";
							sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
							String comments_1 = "";
							comments_1 = db.queryString(sql4);
							if(!"".equals(comments_1)&&comments_1!=null){
								list_comments.add(comments_1);
							}
						}
						String returnString = "";
						returnString+="已保存！\n";
						for(int i=0;i<value_list.size();i++){
							returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
						}
						returnBean.setReturnString(returnString);
						returnBean.setIf_success(true);
						returnBean.setMsg("添加成功！");
						db.commit();
					}else {
						returnBean.setIf_success(false);
						returnBean.setMsg("错误：A78F4W5A11！");
						db.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		}
		
		return returnBean;
	}

	public MapReturnBean addtextarea(MapMarkersAdditionBean bean) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "insert into t_map_textarea_info (sn,lat,lng,text_content,zoom,status)" +
				" values " +
				"(SEQ_T_MAP_TEXTAREA_INFO.NEXTVAL,'"
				+bean.getHidden_lat_1()+"','"
				+bean.getHidden_lng_1()+"','"
				+bean.getTextarea_input1()+"','"
				+bean.getHidden_zoom_1()+"','1') ";
		int i = 0;
		try {
			i = db.insert(sql);
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("文本框新增失败！数据库异常！");
		}
		if(i==1){
			returnBean.setIf_success(true);
			returnBean.setMsg("文本框新增成功！");
		}else{
			returnBean.setIf_success(false);
			returnBean.setMsg("文本框新增失败！");
		}
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public MapReturnBean getTextarea(MapMarkersAdditionBean bean) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "select * from t_map_textarea_info where zoom='"+bean.getNow_map_zoom()+"' and status='1' ";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try {
			list_map = db.query(sql);
			if(list_map.size()>0){
				returnBean.setList_map(list_map);
				returnBean.setIf_success(true);
				returnBean.setMsg("第"+bean.getNow_map_zoom()+"层地图的文本展示框获取成功，一共有("+list_map.size()+")个");
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("文本框获取失败，数据库中不存在第"+bean.getNow_map_zoom()+"层地图的文本展示框的信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("获取第"+bean.getNow_map_zoom()+"层地图的文本展示框失败！数据库异常！");
		}
		return returnBean;
	}
	@SuppressWarnings("unchecked")
	public MapReturnBean giveHiddenDivContent4(MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "";
		String sql1 = "";
		
		sql = "SELECT a.class_columnname AS columnname,"+
			"a.class_comments        AS class_comments,"+
			"a.class_columntype      AS class_columntype,"+
			"a.class_nullable        AS class_nullable,"+
			"a.class_columnsize      AS class_columnsize"+
			" FROM t_map_class_status a,"+
			"t_map_class_info b"+
			" WHERE a.class_tablename =b.class_table_name"+
			" AND b.marker_class      ='"+bean.getHtml_addcircle_select1()+"'"+
//			" AND b.brch_no        ='"+userinfo.getBrch_no()+"'"+
			" AND b.sn                ='"+bean.getHtml_addcircle_select3()+"'"+
			" AND b.class_status      ='1'"+
			" AND a.column_type      ='2'"+
			" AND a.class_columnstatus='1' order by a.sn ";
		sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getHtml_addcircle_select3()+"'";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String table_name = "";
		try {
			list = db.query(sql);
			table_name = db.queryString(sql1);
			if(list.size()>0){
				returnBean.setIf_success(true);
				returnBean.setList_map(list);
				returnBean.setReturnString(table_name);
			}else{
				returnBean.setIf_success(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 添加圈标注
	 * @param upload
	 * @param bean
	 * @return
	 */
	public MapReturnBean addCircleMarkerS(File[] upload,
			MapMarkersAdditionBean bean,UserInfo userinfo) {
		
		String id_str = goodStr(bean.getHtml_id_str());
		String value_str = goodStr(bean.getHtml_value_str());
		
		System.out.println("id:"+id_str);
		System.out.println("value:"+value_str);
		System.out.println("table_name:"+bean.getHtml_table_name());
		System.out.println("line_color:"+bean.getHtml_icon_num());
		System.out.println("location_lat:"+bean.getHtml_location_lat());
		System.out.println("location_lon:"+bean.getHtml_location_lon());
		System.out.println("radius:"+bean.getHtml_circle_radius());
		MapReturnBean returnBean = new MapReturnBean();
		Gson gson = new Gson();
		String radius = bean.getHtml_circle_radius();
		
		List<String> value_list = gson.fromJson(value_str, new TypeToken<List<String>>(){}.getType());//值的list
		List<String> id_list = gson.fromJson(id_str, new TypeToken<List<String>>(){}.getType());//列名的list
		String table_name = bean.getHtml_table_name();//该子类的table_name 用于进行插入操作
		String area_color = bean.getHtml_icon_num();//本次操作的线的颜色代码
		area_color = area_color.replace("@", "#");
		String lat = bean.getHtml_location_lat();//本次操作的lat
		String lon = bean.getHtml_location_lon();//本次操作的lon
		lat = lat.replace(" ", "");
		lon = lon.replace(" ", "");
		lat = lat.replace("null", "");
		lon = lon.replace("null", "");
		lat = lat.replace("undefined", "");
		lon = lon.replace("undefined", "");
		lat = lat.replace("@", "#");
		lon = lon.replace("@", "#");
		System.out.println("lat:"+lat);
		System.out.println("lon:"+lon);
		
		int file_id_num = -1;
		@SuppressWarnings("unused")
		String file_name = "";
		boolean if_upload_file = false;
		boolean if_upload_success = false;
		String upload_img_path = "";//上传到服务器的图片的URL
		String img_exe = "";
		if(upload==null){
			if_upload_file = false;
		}else{
			if_upload_file = true;
			for(int i=0;i<value_list.size();i++){
				if(value_list.get(i).indexOf(".jpg")>-1||value_list.get(i).indexOf(".JPG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpg";
					break;
				}
				else if(value_list.get(i).indexOf(".png")>-1||value_list.get(i).indexOf(".PNG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".png";
					break;
				}
				else if(value_list.get(i).indexOf(".bmp")>-1||value_list.get(i).indexOf(".BMP")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".bmp";
					break;
				}
				else if(value_list.get(i).indexOf(".gif")>-1||value_list.get(i).indexOf(".GIF")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".gif";
					break;
				}
				else if(value_list.get(i).indexOf(".jpeg")>-1||value_list.get(i).indexOf(".JPEG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpeg";
					break;
				}
				else{
					continue;
				}
			}
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
			String uuidFilename = UUID.randomUUID().toString()+img_exe;
			String filepath = filefolderPath+uuidFilename;
			System.out.println("filepath:"+filepath);
			if(upload[0].renameTo(new File(filepath))){
				System.out.println("上传成功！");
				System.out.println("file_id_num:"+file_id_num);
				upload_img_path = "/MarkerImage/"+uuidFilename;
				System.out.println("upload_img_path--------->"+upload_img_path);//TODO
				if_upload_success = true;
			}else{
				System.out.println("上传失败！");
				if_upload_success = false;
			}
		}
		
		if(if_upload_file){
			//带有图片
			if(if_upload_success){
				String sql = "";
				sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
				String sn = "";
				String post_type = "";
				List<String> column_type_list = new ArrayList<String>();
				try {
					sn = db.queryString(sql);//获取子类型表的可用sn
//					sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//					post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
					sql = "select SEQ_T_map_basic.nextval from dual";
					String basic_sn=db.queryString(sql);//basic 表中sn
					String show_text="";
					for(int i=0;i<id_list.size();i++){
						sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
						String column_type = "";
						column_type = db.queryString(sql);
						column_type_list.add(column_type);
					}
					try {
						//开始进行子类型表的数据插入
						String insertSQL = "";
//						insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,radius,";
						insertSQL += "insert into "+table_name+" (sn,";
						for(int i=0;i<id_list.size();i++){
							if(i!=(id_list.size()-1)){
								insertSQL+=id_list.get(i)+",";
							}else{
								insertSQL+=id_list.get(i);
							}
						}
//						insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"','"+radius+"',";
						insertSQL+=") values ("+sn+",";
						for(int i=0;i<id_list.size();i++){
							if(i==0)//20150112，第一个字段为显示字段
							{
								show_text=value_list.get(i);
							}
							if(i!=(id_list.size()-1)){
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"',";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL,";
									}else{
										insertSQL+=value_list.get(i)+",";
									}
								}
							}else{
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"')";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL)";
									}else{
										insertSQL+=value_list.get(i)+")";
									}
								}
							}
						}
						
						db.connect();
						db.startTransaction();
						
						int insertNum = db.insert(insertSQL);
						String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,radius,marker_type,op_brch) values("+
						"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','"+radius+"','4','"+userinfo.getBrch_no()+"')";
						
						int basic_num=db.insert(basic_sql);
						if(insertNum==1&&basic_num==1){
							List<String> list_comments = new ArrayList<String>();
							for(int i=0;i<id_list.size();i++){
								String sql4 = "";
								sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
								String comments_1 = "";
								comments_1 = db.queryString(sql4);
								if(!"".equals(comments_1)&&comments_1!=null){
									list_comments.add(comments_1);
								}
							}
							String returnString = "";
							returnString+="已保存！\n";
							for(int i=0;i<value_list.size();i++){
								returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
							}
							returnBean.setReturnString(returnString);
							returnBean.setIf_success(true);
							returnBean.setMsg("添加成功！");
							db.commit();
						}else {
							returnBean.setIf_success(false);
							returnBean.setMsg("错误：A78F4W5A11！");
							db.rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
						db.rollback();
					}
					finally{
						db.endTransaction();
						db.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("图片上传失败！");
			}
		}else{
			String sql = "";
			sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
			String sn = "";
			String post_type = "";
			List<String> column_type_list = new ArrayList<String>();
			try {
				sn = db.queryString(sql);//获取子类型表的可用sn
//				sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//				post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
				sql = "select SEQ_T_map_basic.nextval from dual";
				String basic_sn=db.queryString(sql);//basic 表中sn
				String show_text="";
				for(int i=0;i<id_list.size();i++){
					sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
					String column_type = "";
					column_type = db.queryString(sql);
					column_type_list.add(column_type);
				}
				try {
					//开始进行子类型表的数据插入
					String insertSQL = "";
//					insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,radius,";
					insertSQL += "insert into "+table_name+" (sn,";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							insertSQL+=id_list.get(i)+",";
						}else{
							insertSQL+=id_list.get(i);
						}
					}
//					insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"','"+radius+"',";
					insertSQL+=") values ("+sn+",";
					for(int i=0;i<id_list.size();i++){
						if(i==0)//20150112，第一个字段为显示字段
						{
							show_text=value_list.get(i);
						}
						if(i!=(id_list.size()-1)){
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"',";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL,";
								}else{
									insertSQL+=value_list.get(i)+",";
								}
							}
						}else{
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"')";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL)";
								}else{
									insertSQL+=value_list.get(i)+")";
								}
							}
						}
					}
					
					db.connect();
					db.startTransaction();
					
					int insertNum = db.insert(insertSQL);
					String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,radius,marker_type,op_brch) values("+
					"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+show_text+"',"+"'1','"+sn+"','"+table_name+"','"+radius+"','4','"+userinfo.getBrch_no()+"')";
					
					int basic_num=db.insert(basic_sql);
					if(insertNum==1&&basic_num==1){
						List<String> list_comments = new ArrayList<String>();
						for(int i=0;i<id_list.size();i++){
							String sql4 = "";
							sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
							String comments_1 = "";
							comments_1 = db.queryString(sql4);
							if(!"".equals(comments_1)&&comments_1!=null){
								list_comments.add(comments_1);
							}
						}
						String returnString = "";
						returnString+="已保存！\n";
						for(int i=0;i<value_list.size();i++){
							returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
						}
						returnBean.setReturnString(returnString);
						returnBean.setIf_success(true);
						returnBean.setMsg("添加成功！");
						db.commit();
					}else {
						returnBean.setIf_success(false);
						returnBean.setMsg("错误：A78F4W5A11！");
						db.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		}
		
		return returnBean;
	}
	@SuppressWarnings("unchecked")
	public MapReturnBean giveHiddenDivContent5(MapMarkersAdditionBean bean,UserInfo userinfo) {
		MapReturnBean returnBean = new MapReturnBean();
		String sql = "";
		String sql1 = "";
		
		sql = "SELECT a.class_columnname AS columnname,"+
			"a.class_comments        AS class_comments,"+
			"a.class_columntype      AS class_columntype,"+
			"a.class_nullable        AS class_nullable,"+
			"a.class_columnsize      AS class_columnsize"+
			" FROM t_map_class_status a,"+
			"t_map_class_info b"+
			" WHERE a.class_tablename =b.class_table_name"+
			" AND b.marker_class      ='"+bean.getHtml_addtextarea_select1()+"'"+
//			" AND b.brch_no        ='"+userinfo.getBrch_no()+"'"+
			" AND b.sn                ='"+bean.getHtml_addtextarea_select3()+"'"+
			" AND b.class_status      ='1'"+
			" AND a.column_type      ='2'"+
			" AND a.class_columnstatus='1' order by a.sn ";
		sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getHtml_addtextarea_select3()+"'";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String table_name = "";
		try {
			list = db.query(sql);
			table_name = db.queryString(sql1);
			if(list.size()>0){
				returnBean.setIf_success(true);
				returnBean.setList_map(list);
				returnBean.setReturnString(table_name);
			}else{
				returnBean.setIf_success(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 添加文本框标注
	 */
	public MapReturnBean addTextAreaMarkerS(File[] upload,
			MapMarkersAdditionBean bean,UserInfo userinfo) {
		
		String id_str = goodStr(bean.getHtml_id_str());
		String value_str = goodStr(bean.getHtml_value_str());
		
		
		System.out.println("id:"+bean.getHtml_id_str());
		System.out.println("value:"+bean.getHtml_value_str());
		System.out.println("table_name:"+bean.getHtml_table_name());
		System.out.println("color:"+bean.getHtml_icon_num());
		System.out.println("location_lat:"+bean.getHtml_location_lat());
		System.out.println("location_lon:"+bean.getHtml_location_lon());
		System.out.println("textarea_zoom:"+bean.getHtml_textarea_zoom());
		System.out.println("文本框显示的文本内容:"+bean.getHtml_textarea_textcontent());
		MapReturnBean returnBean = new MapReturnBean();
		Gson gson = new Gson();
		String zoom = bean.getHtml_textarea_zoom();
		String textarea_textcontent = bean.getHtml_textarea_textcontent();
//		String id_str = bean.getHtml_id_str();
//		String value_str = bean.getHtml_value_str();
		List<String> value_list = gson.fromJson(value_str, new TypeToken<List<String>>(){}.getType());//值的list
		List<String> id_list = gson.fromJson(id_str, new TypeToken<List<String>>(){}.getType());//列名的list
		String table_name = bean.getHtml_table_name();//该子类的table_name 用于进行插入操作
		String area_color = bean.getHtml_icon_num();//本次操作的线的颜色代码
		area_color = area_color.replace("@", "#");
		String lat = bean.getHtml_location_lat();//本次操作的lat
		String lon = bean.getHtml_location_lon();//本次操作的lon
		lat = lat.replace(" ", "");
		lon = lon.replace(" ", "");
		lat = lat.replace("null", "");
		lon = lon.replace("null", "");
		lat = lat.replace("undefined", "");
		lon = lon.replace("undefined", "");
		lat = lat.replace("@", "#");
		lon = lon.replace("@", "#");
		System.out.println("lat:"+lat);
		System.out.println("lon:"+lon);
		
		int file_id_num = -1;
		@SuppressWarnings("unused")
		String file_name = "";
		boolean if_upload_file = false;
		boolean if_upload_success = false;
		String upload_img_path = "";//上传到服务器的图片的URL
		String img_exe = "";
		if(upload==null){
			if_upload_file = false;
		}else{
			if_upload_file = true;
			for(int i=0;i<value_list.size();i++){
				if(value_list.get(i).indexOf(".jpg")>-1||value_list.get(i).indexOf(".JPG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpg";
					break;
				}
				else if(value_list.get(i).indexOf(".png")>-1||value_list.get(i).indexOf(".PNG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".png";
					break;
				}
				else if(value_list.get(i).indexOf(".bmp")>-1||value_list.get(i).indexOf(".BMP")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".bmp";
					break;
				}
				else if(value_list.get(i).indexOf(".gif")>-1||value_list.get(i).indexOf(".GIF")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".gif";
					break;
				}
				else if(value_list.get(i).indexOf(".jpeg")>-1||value_list.get(i).indexOf(".JPEG")>-1){
					file_name = value_list.get(i);
					file_id_num = i;
					img_exe = ".jpeg";
					break;
				}
				else{
					continue;
				}
			}
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
			String uuidFilename = UUID.randomUUID().toString()+img_exe;
			String filepath = filefolderPath+uuidFilename;
			System.out.println("filepath:"+filepath);
			if(upload[0].renameTo(new File(filepath))){
				System.out.println("上传成功！");
				System.out.println("file_id_num:"+file_id_num);
				upload_img_path = "/MarkerImage/"+uuidFilename;
				System.out.println("upload_img_path--------->"+upload_img_path);//TODO
				if_upload_success = true;
			}else{
				System.out.println("上传失败！");
				if_upload_success = false;
			}
		}
		
		if(if_upload_file){
			//带有图片
			if(if_upload_success){
				String sql = "";
				sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
				String sn = "";
				String post_type = "";
				List<String> column_type_list = new ArrayList<String>();
				try {
					sn = db.queryString(sql);//获取子类型表的可用sn
//					sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//					post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
					sql = "select SEQ_T_map_basic.nextval from dual";
					String basic_sn=db.queryString(sql);//basic 表中sn
					String show_text="";
					for(int i=0;i<id_list.size();i++){
						sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
						String column_type = "";
						column_type = db.queryString(sql);
						column_type_list.add(column_type);
					}
					try {
						//开始进行子类型表的数据插入
						String insertSQL = "";
//						insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,zoom,text_content,";
						insertSQL += "insert into "+table_name+" (sn,";
						for(int i=0;i<id_list.size();i++){
							if(i!=(id_list.size()-1)){
								insertSQL+=id_list.get(i)+",";
							}else{
								insertSQL+=id_list.get(i);
							}
						}
//						insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"','"+zoom+"','"+textarea_textcontent+"',";
						insertSQL+=") values ("+sn+",";
						for(int i=0;i<id_list.size();i++){
							
							if(i!=(id_list.size()-1)){
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"',";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL,";
									}else{
										insertSQL+=value_list.get(i)+",";
									}
								}
							}else{
								if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
									insertSQL+="'"+value_list.get(i)+"')";
								}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
									if("".equals(value_list.get(i))||value_list.get(i)==null){
										insertSQL+="NULL)";
									}else{
										insertSQL+=value_list.get(i)+")";
									}
								}
							}
						}
						
						db.connect();
						db.startTransaction();
						
						int insertNum = db.insert(insertSQL);
						String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,zoom,marker_type,op_brch) values("+
						"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+textarea_textcontent+"',"+"'1','"+sn+"','"+table_name+"','"+zoom+"','5','"+userinfo.getBrch_no()+"')";
						
						int basic_num=db.insert(basic_sql);
						if(insertNum==1&&basic_num==1){
							List<String> list_comments = new ArrayList<String>();
							for(int i=0;i<id_list.size();i++){
								String sql4 = "";
								sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
								String comments_1 = "";
								comments_1 = db.queryString(sql4);
								if(!"".equals(comments_1)&&comments_1!=null){
									list_comments.add(comments_1);
								}
							}
							String returnString = "";
							returnString+="已保存！\n";
							for(int i=0;i<value_list.size();i++){
								returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
							}
							returnBean.setReturnString(returnString);
							returnBean.setIf_success(true);
							returnBean.setMsg("添加成功！");
							db.commit();
						}else {
							returnBean.setIf_success(false);
							returnBean.setMsg("错误：A78F4W5A11！");
							db.rollback();
						}
					} catch (Exception e) {
						e.printStackTrace();
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
						db.rollback();
					}
					finally{
						db.endTransaction();
						db.disconnect();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("图片上传失败！");
			}
		}else{
			String sql = "";
			sql = "select SEQ_"+table_name+".nextval from dual";//获取特定表的SEQ
			String sn = "";
			String post_type = "";
			List<String> column_type_list = new ArrayList<String>();
			try {
				sn = db.queryString(sql);//获取子类型表的可用sn
//				sql = "select post_class from t_map_class_info where class_table_name='"+table_name+"' and class_status='1' ";
//				post_type = db.queryString(sql);//选出表的对应的部门类型用于插入post_flag
				sql = "select SEQ_T_map_basic.nextval from dual";
				String basic_sn=db.queryString(sql);//basic 表中sn
				String show_text="";
				
				for(int i=0;i<id_list.size();i++){
					sql = "select class_columntype from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
					String column_type = "";
					column_type = db.queryString(sql);
					column_type_list.add(column_type);
				}
				try {
					//开始进行子类型表的数据插入
					String insertSQL = "";
//					insertSQL += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag,mc_markers,zoom,text_content,";
					insertSQL += "insert into "+table_name+" (sn,";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							insertSQL+=id_list.get(i)+",";
						}else{
							insertSQL+=id_list.get(i);
						}
					}
//					insertSQL+=") values ("+sn+",'1','"+lat+"','"+lon+"','"+post_type+"','"+area_color+"','"+zoom+"','"+textarea_textcontent+"',";
					insertSQL+=") values ("+sn+",";
					for(int i=0;i<id_list.size();i++){
						if(i!=(id_list.size()-1)){
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"',";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL,";
								}else{
									insertSQL+=value_list.get(i)+",";
								}
							}
						}else{
							if("1".equals(column_type_list.get(i))||"4".equals(column_type_list.get(i))){
								insertSQL+="'"+value_list.get(i)+"')";
							}else if("2".equals(column_type_list.get(i))||"3".equals(column_type_list.get(i))){
								if("".equals(value_list.get(i))||value_list.get(i)==null){
									insertSQL+="NULL)";
								}else{
									insertSQL+=value_list.get(i)+")";
								}
							}
						}
					}
					
					db.connect();
					db.startTransaction();
					
					int insertNum = db.insert(insertSQL);
					String basic_sql="insert into t_map_class_basics(sn,lat,lng,mc_markers,show_text,status_flag,marker_sn,table_name,zoom,marker_type,op_brch) values("+
					"'"+basic_sn+"','"+lat+"','"+lon+"','"+area_color+"','"+textarea_textcontent+"',"+"'1','"+sn+"','"+table_name+"','"+zoom+"','5','"+userinfo.getBrch_no()+"')";
					
					int basic_num=db.insert(basic_sql);
					if(insertNum==1&&basic_num==1){
						List<String> list_comments = new ArrayList<String>();
						for(int i=0;i<id_list.size();i++){
							String sql4 = "";
							sql4 = "select class_comments from t_map_class_status where class_tablename='"+table_name+"' and column_type='2' and class_columnname='"+id_list.get(i)+"' and class_columnstatus='1' ";
							String comments_1 = "";
							comments_1 = db.queryString(sql4);
							if(!"".equals(comments_1)&&comments_1!=null){
								list_comments.add(comments_1);
							}
						}
						String returnString = "";
						returnString+="已保存！\n";
						for(int i=0;i<value_list.size();i++){
							returnString+=list_comments.get(i)+":"+value_list.get(i)+"\n";
						}
						returnBean.setReturnString(returnString);
						returnBean.setIf_success(true);
						returnBean.setMsg("添加成功！");
						db.commit();
					}else {
						returnBean.setIf_success(false);
						returnBean.setMsg("错误：A78F4W5A11！");
						db.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		}
		
		return returnBean;
	}
}
