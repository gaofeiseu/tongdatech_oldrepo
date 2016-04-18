package com.tongdatech.sys.init;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.OptionConverter;
import com.tongdatech.sys.service.ParamService;
import com.tongdatech.sys.util.ConfigUtil;
import com.tongdatech.sys.util.ParamsUtil;







/**
 * web��������ʱ<br>
 * ��proj.properties��ʼ������<br>
 * (��������������ʱ����  <b>Unfinished</b>)
 * @author xl 
 * @version   2014-2-27 ����01:15:19

 */
public class InitProj extends HttpServlet{


	private static final long serialVersionUID = 6703084909903302060L;
	private static final Logger LOG = Logger.getLogger(InitProj.class);

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		
		String webPath = this.getServletContext().getRealPath("/");
		System.setProperty("webapp.root", webPath);
		String domPath = System.getProperty("user.dir");
		
		String projName     = "ϵͳ���";
		String projShort    = "ϵͳ";
		String projLogoImg  = "/weblib/img/logo.png";
		String projLoginImg = "/weblib/login/login.png";
		String projIcon = "/weblib/login/proj.ico";
		String projTimeOut ="30";
		String filePath     = domPath+"/upload";
		
		
		boolean isTabs      =false;
		String svn          = "�ް汾��";
		try {
			Properties properties = ConfigUtil.loadProperties("proj");
			projName     = properties.getProperty("projName");
			projShort    = properties.getProperty("projShort");
			projLogoImg  = properties.getProperty("projLogoImg");
			projLoginImg = properties.getProperty("projLoginImg");
			filePath     = properties.getProperty("filePath");
			filePath     = OptionConverter.substVars(filePath,properties);
			projIcon     = properties.getProperty("projIcon");
			isTabs       = Boolean.parseBoolean(properties.getProperty("isTabs"));
			projTimeOut  = properties.getProperty("projTimeOut");
			//svn          = properties.getProperty("svn");
		} catch (Exception e) {
			LOG.error("proj�޷�����ʹ��Ĭ������", e);
		}
		System.setProperty("svn",svn);
		//System.setProperty("user.timezone","Asia/Shanghai"); 
		
		this.getServletContext().setAttribute("projName",projName);
		this.getServletContext().setAttribute("projShort",projShort);
		this.getServletContext().setAttribute("projLogoImg",projLogoImg);
		this.getServletContext().setAttribute("projLoginImg",projLoginImg);
		this.getServletContext().setAttribute("projLoginImg",projLoginImg);
		this.getServletContext().setAttribute("projIcon",projIcon);
		this.getServletContext().setAttribute("isTabs",isTabs);
		this.getServletContext().setAttribute("projTimeOut",projTimeOut);
		
		ParamService paramService = new ParamService();
		try {
			Map<?, ?> paramMap = paramService.load();
			this.getServletContext().setAttribute(ParamsUtil.ParamSys, paramMap.get(ParamsUtil.ParamSys));
			this.getServletContext().setAttribute(ParamsUtil.ParamJson,paramMap.get(ParamsUtil.ParamJson));
		} catch (Exception e1) {
			LOG.error("�޷���ʼ������Param", e1);
		}
		try {
			File file=new File(filePath);
			boolean rs_f=true;
			if(!file.exists())rs_f=file.mkdirs();
			if(rs_f){
				this.getServletContext().setAttribute("filePath",filePath);
			}else throw new Exception("mkdirʧ��");
		}catch (Exception e) {
			LOG.error("�޷���ʼ�������ļ��洢Ŀ¼��"+filePath, e);
		}
		
		
		
		
	}

}
