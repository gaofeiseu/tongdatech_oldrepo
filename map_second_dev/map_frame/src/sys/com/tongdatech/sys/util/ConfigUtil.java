package com.tongdatech.sys.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * 加载配置文件公用方法
 * @author xl
 * @version   2014-2-27 下午01:15:19
 */
public class ConfigUtil {
	private static final Logger LOG = Logger.getLogger(ConfigUtil.class);
	
	/**
	 * @param  name properties文件名称
	 * @return properties对象
	 * @throws Exception
	 */
	public static Properties loadProperties(String name) throws Exception{
		Properties properties=new Properties();
        URL settingsUrl = ClassLoaderUtil.getResource(name + ".properties",ConfigUtil.class);
        
        if (settingsUrl == null) {
            if (LOG.isDebugEnabled()) {
        	LOG.debug(name + ".properties missing");
            }
            return null;
        }
		InputStream in = null;
        try {
        	in = settingsUrl.openStream();
            properties.load(in);
        } catch (Exception e) {
            throw new Exception("Could not load " + name + ".properties:" + e, e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                    if (LOG.isTraceEnabled()) {
                	LOG.warn("Unable to close input stream", io);
                    }
                }
            }
        }
		return properties;
	}

}
