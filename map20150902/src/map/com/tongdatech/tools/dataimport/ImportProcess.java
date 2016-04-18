package com.tongdatech.tools.dataimport;

import java.util.List;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.JspMsg;

public interface ImportProcess {

	/**
	 * 保存导入数据并控制跳转
	 * @param context
	 * @param userInfo
	 * @return JspMsg
	 * @throws Exception
	 */
	public JspMsg saveData(ImportContext context,UserInfo userInfo) throws Exception;
	
	/**
	 * 获取列信息
	 * @return ImportCol
	 */
	public List<ImportCol> getCols();
}
