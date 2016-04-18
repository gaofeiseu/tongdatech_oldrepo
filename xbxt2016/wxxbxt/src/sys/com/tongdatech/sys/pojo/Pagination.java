package com.tongdatech.sys.pojo;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.action.LoginAction;
import com.tongdatech.sys.annotation.PageParam;
import com.tongdatech.sys.util.ReflectionUtil;



/**
 * ͨ�÷�ҳ�����洢����
 * @author xl
 * 
 */
public class Pagination {
	
	private static Logger log =  Logger.getLogger(LoginAction.class);
	
	/**��ʼ����*/
	private int stnum;
	/**��������*/
	private int ednum;
	
	/**�ܼ�*/
	private int total;          
	/**ÿ������*/
	private int rows;           
	/**ҳ��*/
	private int page;           
	/**��ҳ��ť��Ŀ��Ŀ(������ǰһҳ��һҳ)*/
	private int btnNum;         
	/**��ѯURI*/
	private String uri;         
	/**����URL*/
	private String back_url;    
	/**��ѯ����*/
	private Map<String,String[]> queryParams; 

	/**action ��Ϣ*/
	private ActionSupport action;

	/** String annotationName ��ע����*/
	private static String annotationName =PageParam.class.getName();

	/**
	 * ���췽��
	 * @param stnum
	 * @param ednum
	 * @param total
	 * @param rows
	 * @param page
	 * @param uri
	 * @param back_url
	 */
	public Pagination(int stnum, int ednum, int total, int rows, int page,
			String uri, String back_url) {
		super();
		this.stnum = stnum;
		this.ednum = ednum;
		this.total = total;
		this.rows = rows;
		this.page = page;
		this.uri = uri;
		this.back_url = back_url;
	}
	/**
	 * ��Action�в���ת��ΪURL<br>
	 * e.g.<br>
	 * List<User> users =>>users.user_id=1&users.user_id=2&users.user_id=3<br>
	 * String[] ids =>>ids=1&ids=2&ids=3<br>
	 * @return url
	 */
	public String getURLParam(){
		if(queryParams==null)initParams();
		StringBuffer sb =new  StringBuffer();
		
		for(String key:queryParams.keySet()){
			String[] values = queryParams.get(key);
			for(String value:values){
				if(value==null||"null".equalsIgnoreCase(value))continue;
				sb.append("&"+key+"="+value);
			}
		}
		try {
			if(back_url!=null){
				String en_url = URLEncoder.encode(back_url, "GBK");
				sb.append("&back_url="+en_url);
			}
			
		} catch (UnsupportedEncodingException e) {
			log.error("PAGE",e);
		}
		return sb.toString();
	}

    
	/**
	 * ˽�з���
	 * ��Action�в�����ʼ��
	 */
	private void initParams(){
		queryParams=new HashMap<String, String[]>();
		if (action instanceof ModelDriven) {
            ModelDriven<?> modelDriven = (ModelDriven<?>) action;        
            Object model = modelDriven.getModel();
            getAnnotation("",model);
        }
		getAnnotation("",action);
	}

	/**
	 * ˽�з���
	 * ���ݱ�ע PageParam @see com.tongdatech.sys.annotation.PageParam
	 * ��ϲ�����queryParams
	 * @param perfix
	 * @param obj
	 */
	private void getAnnotation(String perfix,Object obj){
		if(obj==null) return;
		
		if(obj instanceof List){
			List<?> list=(List<?>)obj;	
			for(Object one:list){
				getAnnotation(perfix,one);
			}
			return;
			
		}
		if(obj instanceof Object[]){
			Object[] objs=(Object[])obj;
			for(Object one:objs){
				getAnnotation(perfix,one);
			}
			return;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		Annotation[] annotations;
		for (Field field : fields) {

			annotations = field.getAnnotations();

			for (Annotation annotation : annotations) {
				if (annotationName.equals(annotation.annotationType().getName())) {
					String newperfix = "";
					if("".equals(perfix))newperfix=field.getName();
					else newperfix=perfix+"."+field.getName();
					
					
			        Object value = null;
					try {
						value = ReflectionUtil.invokeGetMethod(obj,field);
						if(field.isSynthetic()){
							getAnnotation(newperfix,value);					
						}else{
							String[] values=queryParams.get(newperfix);
							List<String> listValues = null;
							if(values == null ) listValues = new ArrayList<String>();
							else listValues = Arrays.asList(values);
							
							listValues.add(ReflectionUtil.parseParams(value));
							int size=listValues.size();
							String[] newValues  = listValues.toArray(new String[size]);
							queryParams.put(newperfix, newValues);
						}
					} catch (Exception e) {
						log.error("PAGE",e);
					} 

				}
			}
		}
		
		
		
	}
	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}
	/**
	 * @param log the log to set
	 */
	public static void setLog(Logger log) {
		Pagination.log = log;
	}
	/**
	 * @return the stnum
	 */
	public int getStnum() {
		return stnum;
	}
	/**
	 * @param stnum the stnum to set
	 */
	public void setStnum(int stnum) {
		this.stnum = stnum;
	}
	/**
	 * @return the ednum
	 */
	public int getEdnum() {
		return ednum;
	}
	/**
	 * @param ednum the ednum to set
	 */
	public void setEdnum(int ednum) {
		this.ednum = ednum;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the btnNum
	 */
	public int getBtnNum() {
		return btnNum;
	}
	/**
	 * @param btnNum the btnNum to set
	 */
	public void setBtnNum(int btnNum) {
		this.btnNum = btnNum;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return the back_url
	 */
	public String getBack_url() {
		return back_url;
	}
	/**
	 * @param back_url the back_url to set
	 */
	public void setBack_url(String back_url) {
		this.back_url = back_url;
	}
	/**
	 * @return the queryParams
	 */
	public Map<String, String[]> getQueryParams() {
		return queryParams;
	}
	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(Map<String, String[]> queryParams) {
		this.queryParams = queryParams;
	}
	/**
	 * @return the action
	 */
	public ActionSupport getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(ActionSupport action) {
		this.action = action;
	}
	/**
	 * @return the annotationName
	 */
	public static String getAnnotationName() {
		return annotationName;
	}
	/**
	 * @param annotationName the annotationName to set
	 */
	public static void setAnnotationName(String annotationName) {
		Pagination.annotationName = annotationName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pagination [stnum=" + stnum + ", ednum=" + ednum + ", total="
				+ total + ", rows=" + rows + ", page=" + page + ", btnNum="
				+ btnNum + ", uri=" + uri + ", back_url=" + back_url
				+ ", queryParams=" + queryParams + ", action=" + action + "]";
	}


	
	


}
