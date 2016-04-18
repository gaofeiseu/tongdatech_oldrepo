package com.tongdatech.tools.dataimport;

import java.util.List;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.JspMsg;

public interface ImportProcess {

	/**
	 * ���浼�����ݲ�������ת
	 * @param context
	 * @param userInfo
	 * @return JspMsg
	 * @throws Exception
	 */
	public JspMsg saveData(ImportContext context,UserInfo userInfo) throws Exception;
	
	/**
	 * ��ȡ����Ϣ
	 * @return ImportCol
	 */
	public List<ImportCol> getCols();
}
