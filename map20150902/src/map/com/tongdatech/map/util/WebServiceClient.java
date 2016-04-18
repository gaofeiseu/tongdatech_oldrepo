
package com.tongdatech.map.util;

import java.net.URL;

import org.codehaus.xfire.client.Client;

/**
 * Web Service 调用客户端
 */
public class WebServiceClient {
		
	/**
	 * 调用数据库营销平台 web service 服务
	 * @param methodName
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String invokeBDM(String methodName,Object[] params) throws Exception{
		String wsdlPath = ServiceConfig.get("MAP_URL");
		
		URL wsdlUrl = new URL(wsdlPath);
		Client client = new Client(wsdlUrl);
		Object[] result = client.invoke(methodName, params);
		
		String json = (String)result[0];		
		return json;
		
	}

}
