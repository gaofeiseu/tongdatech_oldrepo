/**
 * File name:DataDictService.java
 * Create author:XY
 * Create date:2014-3-25
 */
package com.tongdatech.sys.demo.service;

import java.util.List;
import java.util.Map;

import com.tongdatech.sys.demo.bean.DataDict;
import com.tongdatech.sys.demo.dao.DataDictDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

/**
 * @author XY
 *
 */
public class DataDictService {
	
	/**
	 * ��ȡ���ݿ����
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> tableList(DataDict dataDict) throws Exception{
		DataDictDao dataDictDao = new DataDictDao();
		return dataDictDao.tableList(dataDict);
		
	}
	
	/**
	 * ��ȡָ�����ݱ���ϸ��Ϣ
	 * @param pagination
	 * @param dataDict
	 * @return
	 * @throws Exception
	 */
	public PageUI query(Pagination pagination, String tablename) throws Exception {
		// TODO Auto-generated method stub
		DataDictDao dataDictDao = new DataDictDao();
		return dataDictDao.query(pagination,tablename);
	}
	
	/**
	 * ��ȡ���ݱ���ϸ��Ϣ
	 * @param pagination
	 * @param dataDict 
	 * @param dataDict
	 * @return
	 * @throws Exception
	 */
	public PageUI list(Pagination pagination, DataDict dataDict) throws Exception {
		// TODO Auto-generated method stub
		DataDictDao dataDictDao = new DataDictDao();
		return dataDictDao.list(pagination,dataDict);
	}
}
