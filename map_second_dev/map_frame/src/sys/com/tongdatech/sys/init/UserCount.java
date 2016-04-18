package com.tongdatech.sys.init;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.tongdatech.sys.bean.UserInfo;

/**
 * ��¼��ǰ��¼�û���Ŀ       <br>
 * @author xl 
 * @version   2014-2-28 ����11:31:30
 */
public class UserCount implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static Logger log = Logger.getLogger(UserCount.class);

	/** int count ����ͳ�Ʊ���*/
	public static int count = 0;

	/**
	 * �û�ͳ��
	 */
	public UserCount() {
		log.info("��ǰ��������" + count);
	}

	/**
	 * ���count
	 * @return int
	 */
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		log.info("sessionCreated");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("sessionDestroyed");
		UserInfo userInfo=(UserInfo)(se.getSession()).getAttribute("userInfo");
		if (userInfo != null) {
			if (count <= 0)
				count = 0;
			else
				count--;
			log.info("��ǰ��������" + count);

		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		String name = arg0.getName();
		if ("userInfo".equals(name)) {
			count++;
			log.info("��ǰ��������" + count);
		}

	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {

	}
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}
}
