package com.tongdatech.map.util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.time.DateUtils;

public class RZTimerListener implements ServletContextListener {
	 public static final long PERIOD_DAY = DateUtils.MILLIS_PER_DAY;
	 public static final long PERIOD_WEEK = PERIOD_DAY * 7;
	 public static final long PERIOD_TASK = DateUtils.MILLIS_PER_MINUTE*20;//DateUtils.MILLIS_PER_HOUR * 4;
	 public static final long NO_DELAY = 0;
	 public static final long HAVE_DELAY = 30000;//����������֮����ʱ20��ִ��
	 private Timer timer;
	 
	 public void contextInitialized(ServletContextEvent event) {
		 timer = new Timer("���ݿ����",true);
		 timer.schedule(new RZTimerTask(),HAVE_DELAY, PERIOD_TASK);
	 }
	 public void contextDestroyed(ServletContextEvent event) {
		 if(timer!=null){
			 timer.cancel();
		 }
	 }
}
