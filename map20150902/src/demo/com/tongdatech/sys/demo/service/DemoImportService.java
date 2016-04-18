package com.tongdatech.sys.demo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.ImportResult;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.util.DBUtil;
import com.tongdatech.tools.dataimport.ImportCol;
import com.tongdatech.tools.dataimport.ImportContext;
import com.tongdatech.tools.dataimport.ImportProcess;
import com.tongdatech.tools.dataimport.ImportTool;

public class DemoImportService implements ImportProcess,Serializable{

	/** long serialVersionUID*/
	private static final long serialVersionUID = 8395709750342576004L;

	@Override
	public JspMsg saveData(ImportContext context,UserInfo userInfo) throws Exception {
		JspMsg rs = new JspMsg();
		DBUtil db = new DBUtil();
		
		ImportTool tool = context.getTool();//获取工具类
		Map<Integer, String> colsMap = context.getCols();
		Iterator<List<String>> it = tool.getIterator();//获取迭代器
		String sql="";
		int im_sn=db.queryInt("select SEQ_T_SYS_IMPORT.nextval from dual ");
		
		//如果没有列选择
		Set<Integer> include=null;
		if(colsMap==null||colsMap.isEmpty()){
			sql="insert into t_sys_demo_import (im_sn,col1,col2,col3,col4)values("+im_sn+",?,?,?,?)";
			Integer[] x={0,1,2,3};
			include=new TreeSet<Integer>(Arrays.asList(x));

		}else{
			include=colsMap.keySet();
			String colstr="";
			String valstr="";
			for(String col:colsMap.values()){
				colstr+=","+col;
				valstr+=",?";
			}
			sql="insert into t_sys_demo_import (im_sn"+colstr+")values("+im_sn+""+valstr+")";
		}
        
		db.connect();
		db.startTransaction();
		try{
			ImportResult irs = db.BatDML(sql, it, include);
			String sql_log=tool.logSql(irs,userInfo,im_sn,this.getClass().getName());
			db.insert(sql_log);
			if (irs.getSuccess()>0){
				rs.setSuccess(true);
				rs.setMsg("共有数据"+irs.getTotal()+"条，成功导入"+irs.getSuccess()+"条");
				rs.setBackParam(irs);
			}else{
				rs.setMsg("导入失败");
			}
		}catch (Exception e) {
			db.rollback();
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		
		
		
		
		
		return rs;
	}

	@Override
	public List<ImportCol> getCols() {
		List<ImportCol> rs=new ArrayList<ImportCol>();
		rs.add(new ImportCol("col1","列1"));
		rs.add(new ImportCol("col2","列2"));
		rs.add(new ImportCol("col3","列3"));
		rs.add(new ImportCol("col4","列4"));
		return rs;
	}

	

}
