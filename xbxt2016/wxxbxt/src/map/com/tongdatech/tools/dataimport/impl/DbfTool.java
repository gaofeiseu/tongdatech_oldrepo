package com.tongdatech.tools.dataimport.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.tools.dataimport.ImportTool;

public class DbfTool extends ImportTool
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4654324111646L;
	private int st_row=0;
	private int ed_row=Integer.MAX_VALUE;

	@Override
	public PageUI beforeRender() throws IOException 
	{
		PageUI pg = new PageUI();
		
		
		InputStream fis = null;  
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> li=new ArrayList<String>();
		Set<String> columns=new TreeSet<String>();
        try {  
            // 读取文件的输入流  
            fis = new FileInputStream(file);  
            // 根据输入流初始化一个DBFReader实例，用来读取DBF文件信息  
            DBFReader dReader = new DBFReader(fis);  
            // 调用DBFReader对实例方法得到path文件中字段的个数  
            int fCount = dReader.getFieldCount();  
            // 取出字段信息  
            for (int i = 0; i < fCount; i++) {  
                DBFField field = dReader.getField(i);  
                li.add(field.getName());
            }  
            String[] str=li.toArray(new String[0]);
            Object[] rowValues;  
            int row=0;
            // 一条条取出path文件中记录  
            while ((rowValues = dReader.nextRecord()) != null) {  
            	row++;
            	if(row>=st_row&&row<=ed_row)
            	{
            		Map<String,String> mp = new HashMap<String,String>();
            		mp.put(ROW_NUM, String.valueOf(row));
            		for (int i = 0; i<rowValues.length-1; i++) 
            		{  
            			//System.out.println(rowValues[i]);  
            			mp.put(str[i],rowValues[i].toString());
            			columns.add(str[i]);
            		}  
            		list.add(mp);
            	}
            }  
        } catch (Exception e) {   
        } finally {  
            try {  
                if(fis!=null)fis.close();  
                pg.setRows(list);
                pg.setColumns(new ArrayList<String>(columns));
            } catch (Exception e) {  
            }  
        }  
        
	    return pg; 
		
		
	}

	@Override
	public void parseParam() 
	{
		String str_st_row=get("dbf_st_row");
		String str_ed_row=get("dbf_ed_row");
		try{
			st_row=Integer.parseInt(str_st_row);
		}catch (Exception e) {
		}
		try{
			ed_row=Integer.parseInt(str_ed_row);
		}catch (Exception e) {
		}
	}

	@Override
	public Iterator<List<String>> getIterator() 
	{
		class DbfIterator implements Iterator<List<String>>
		{
			private DBFReader dReader;
			private int index;
			private Object[] rowValues;
			private List<String> li=new ArrayList<String>();
			private FileInputStream fi;
			
			public DbfIterator() 
			{
				try {
					fi = new FileInputStream(file);
					dReader = new DBFReader(fi);
				} catch (DBFException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				index = 0;
			}
			
			@Override
			public boolean hasNext() 
			{
				try
				{
					while((rowValues=dReader.nextRecord())!=null)
					{
						index++;
						li.clear();
						if(index>=st_row&&index<=ed_row)
						{
							for (int i = 0; i<rowValues.length-1; i++) 
							{
								li.add(rowValues[i].toString());
							}
							return true;
						}
					}
					
				}
				catch(Exception e)
				{
					return false;
				}finally{
					try {
						if(fi!=null)fi.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				return false;
			}

			@Override
			public List<String> next() {
				// TODO Auto-generated method stub
				return li;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		}
		DbfIterator dbfIterator = new DbfIterator();
		return dbfIterator;
	}
	
}