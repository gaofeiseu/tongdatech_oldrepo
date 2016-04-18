package com.tongdatech.map.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoundListThread implements Runnable {
	List<Map<String,Object>> d_datalist;
	List<Map<String,Object>> s_datalist;
	BigDecimal startx ;
	BigDecimal starty;
	BigDecimal endx;
	BigDecimal endy;
	BoundListThread(List<Map<String,Object>> d_datalist,List<Map<String,Object>> s_datalist,BigDecimal startx ,BigDecimal starty,BigDecimal endx,BigDecimal endy)
	{
		this.d_datalist=d_datalist;
		this.s_datalist=s_datalist;
		this.startx=startx ;
		this.starty=starty;
		this.endx=endx;
		this.endy=endy;
	}
	
	public  List<Map<String,Object>>  getBoundsList(List<Map<String,Object>> datalist,BigDecimal startx ,BigDecimal starty,BigDecimal endx,BigDecimal endy)
	{
	 
		Map<String,Object> tempMap=new HashMap<String,Object>();
		String latArray[];
		String lngArray[];
 
		
		for(int i=0;i<datalist.size();i++)
		{
			tempMap=datalist.get(i);
			latArray=tempMap.get("lat").toString().split("#");
			lngArray=tempMap.get("lng").toString().split("#");

			for(int j=0;j<latArray.length;j++)
			{
				if((startx.compareTo(new BigDecimal(latArray[j].replace(" ", "")))<0 && endx.compareTo(new BigDecimal(latArray[j].replace(" ", "")))>0) && (starty.compareTo(new BigDecimal(lngArray[j].replace(" ", "")))<0 && endy.compareTo(new BigDecimal(lngArray[j].replace(" ", "")))>0)){
					//ÔÚ·¶Î§ÄÚ
					this.d_datalist.add(tempMap);
					break;
				}
			}
		}
		return d_datalist;
	}
	@Override
	public void run() {
		 
		this.d_datalist=this.getBoundsList(this.s_datalist,this.startx,this.starty,this.endx,this.endy);
	}

}
