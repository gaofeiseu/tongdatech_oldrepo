package com.tongdatech.xbxt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.xbxt.dao.XBDao;

public class ThreadFactory {
	private static final String WORKING = "working";
	private static final String FINISH = "finish";
	private static int pool_size = 10;
	private ExecutorService fixedThreadPool = null;
	private int total = 0;
	private XBDao dao = new XBDao();
	private AtomicInteger success_num = new AtomicInteger(0);
	private AtomicInteger error_num = new AtomicInteger(0);
	private List<AjaxMsg> list_result = new ArrayList<AjaxMsg>();
	public ThreadFactory(int _pool_size){
		pool_size = _pool_size;
		fixedThreadPool = Executors.newFixedThreadPool(_pool_size);
	}
	public ThreadFactory(){
		fixedThreadPool = Executors.newFixedThreadPool(pool_size);
	}
	public void start(List<String> list_mailno) throws Exception {
		list_result = new ArrayList<AjaxMsg>();
		dao.setXBInterfaceStatus(WORKING);
		total = list_mailno.size();
		for(String mailno : list_mailno){
			final String mail_no = mailno;
			fixedThreadPool.execute(new Runnable(){
				@Override
				public void run() {
					AjaxMsg am = new AjaxMsg();
					am = XBUtils.get_ttxx(mail_no);
					if(am.isSuccess()){
						success_num.addAndGet(1);
						list_result.add(am);
					}else{
						error_num.addAndGet(1);
					}
					if(total==(success_num.get()+error_num.get())){
						dao.setXBInterfaceStatus(FINISH);
						dao.insertInterfaceDetail(list_result);
						dao.log_interface_info("接口获取完毕，总任务数【"+total+"】，其中可以获取到数据数【"+success_num.get()+"】，无法调用接口数【"+error_num.get()+"】");
						success_num.set(0);
						error_num.set(0);
					}
				}
			});
		}
	}
}
