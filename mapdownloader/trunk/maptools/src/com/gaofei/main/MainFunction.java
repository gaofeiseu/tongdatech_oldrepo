package com.gaofei.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.gaofei.bean.BaseBean;
import com.gaofei.bean.TilesBean;
import com.gaofei.util.AtomicCounter;
import com.gaofei.util.GetStartConfig;
import com.gaofei.util.PrepareDownload;
import com.gaofei.util.PropertyUtils;
import com.gaofei.util.SplitFactory;
import com.gaofei.util.ValidationUtil;

public class MainFunction {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {
		try{
			BaseBean startConfigBean = new BaseBean();
			boolean if_run = false;
			if(args.length==1){
				if("-help".equals(args[0])){
					System.out.println("带参数的命令示例");
					System.out.println("\t\tjava -jar mapdownloader.jar 保存路径 下载图片类型 起始层数 结束层数 起始经度 起始纬度 结束经度 结束纬度");
					System.out.println("\t\tjava -jar mapdownloader.jar F:/map1234 1#2#3 1 17 118.417774 32.391383 119.243122 31.565489");
				}else if("-number".equals(args[0])){
					System.out.println(AtomicCounter.getValue());
				}
			}
			else if(args.length==8){
				ValidationUtil.validate(args);
				startConfigBean = new BaseBean(args);
				if_run = true;
			}else{
				if(PropertyUtils.validate()){
					System.out.println("=========================读取配置文件获取参数========================");
					startConfigBean = PropertyUtils.getBean();
				}else{
					System.out.println("=========================获取配置文件失败，启用演示模式========================");
					startConfigBean = GetStartConfig.getStartConfigBean();
				}
				if_run = true;
			}
			if(if_run){
				Date st_time = new Date();
				System.out.println("=====================任务开始=="+st_time.toString()+"======================");
				List<TilesBean> list_tilesBean = SplitFactory.handleConfigBean(startConfigBean);
				AtomicCounter.setValue(0);
				PrepareDownload.run(list_tilesBean);
				Date ed_time = new Date();
				System.out.println("=====================任务完成=="+ed_time.toString()+"======================");
				System.out.println("任务开始时间：\t\t"+format.format(st_time));
				System.out.println("任务结束时间：\t\t"+format.format(ed_time));
				System.out.println("下载图片总数：\t\t"+AtomicCounter.getValue());
				AtomicCounter.setValue(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
