package com.tongdatech.tools.dataimport.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


import com.csvreader.*;

import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.tools.dataimport.ImportTool;

public class CsvTool extends ImportTool
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123151762711351L;
	private int st_row=0;
	private int ed_row=Integer.MAX_VALUE;
	
	
	/**
	 * 
	 */
	@Override
	public void parseParam() 
	{
		String str_st_row=get("csv_st_row");
		String str_ed_row=get("csv_ed_row");
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
		class CsvIterator implements Iterator<List<String>>
		{
			private CsvReader creader;
			private int index;

			private List<String[]> list=new ArrayList<String[]>();
			private List<String> li=new ArrayList<String>();
			private FileReader fr;
			BufferedReader reader;
	    	public CsvIterator()
	    	{
				try {
					fr=new FileReader(file);
					reader = new BufferedReader(fr);
					creader = new CsvReader(reader,delimiter);
					creader.readRecord();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    index=0;
	    	}
			@Override
			public boolean hasNext() 
			{
				try 
				{
					while(creader.readRecord())
					{
						index++;
						if(index>=st_row&&index<=ed_row)
						{
							list.clear();
							li.clear();
							list.add(creader.getValues());
							String[] str=list.get(0);
							for(String s:str)li.add(s);
							return true;
						}
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}finally{
					try {
						if(creader!=null)creader.close();
						if(reader!=null)reader.close();
						if(fr!=null)fr.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
				return false;
			}

			@Override
			public List<String> next() 
			{
				return li;
			}

			@Override
			public void remove() 
			{
			}
			
		}
		CsvIterator csvIterator = new CsvIterator();
		return csvIterator;
	}

	
	@Override
	public PageUI beforeRender() throws IOException 
	{
		PageUI pg = new PageUI();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String[]> headList=new ArrayList<String[]>();
		Set<String> columns=new TreeSet<String>();
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		CsvReader creader = new CsvReader(reader,delimiter);
		
		creader.readRecord();
		headList.add(creader.getValues());
		String[] str=headList.get(0);
		
		int count=creader.getColumnCount();
		
		int row=0;
		while(creader.readRecord())
		{
			row++;
			if(row>=st_row&&row<=ed_row)
			{
				Map<String,String> mp = new HashMap<String,String>();
				mp.put(ROW_NUM, String.valueOf(row));
					for(int i=0;i<count;i++)
					{
						mp.put(str[i],creader.get(i));
						columns.add(str[i]);
					}
			list.add(mp);
			}
		}
		creader.close();
		reader.close();
		 pg.setRows(list);
		 pg.setColumns(new ArrayList<String>(columns));
		 
		return pg;
	}
}