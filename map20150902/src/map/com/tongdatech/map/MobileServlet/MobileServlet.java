package com.tongdatech.map.MobileServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import com.tongdatech.map.util.AppLog;
import com.tongdatech.map.util.CommonUtil;
import com.tongdatech.map.util.WebServiceClient;

public class MobileServlet extends HttpServlet {
	private static final long serialVersionUID = -6465735729772207288L;

	private static final String MAP_PHONE_CHECKUSER = "map_phone_checkuser";
	private static final String MAP_PHONE_SUBMITDATA = "map_phone_submitdata";
	private static final String METHOD_PARAM_NAME = "method";

	public MobileServlet() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("=====BDMServlet=====ST=====");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		String methodParam = CommonUtil.getNotNullStr(req
				.getParameter(METHOD_PARAM_NAME));

		if ("map_phone_checkuser".equals(methodParam)) {
			map_phone_checkuser(req, resp);
		} else if ("map_phone_submitdata".equals(methodParam)) {
			map_phone_submitdata(req, resp);
		} else if ("checkUpdate".equals(methodParam)) {
			checkUpdate(req, resp);
		} else {
			System.out.println("找不到对应方法：" + methodParam);
		}

		System.out.println("=====BDMServlet=====ED=====");
	}

	private void map_phone_submitdata(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("mobile_servlet------------>map_phone_submitdata");
		String params = CommonUtil.getNotNullStr(req.getParameter("params"));
		System.out.println("map手机端的参数：" + params);
		String[] paramsArr = params.split(",");
		Object[] objArr = new Object[5];
		objArr[0] = paramsArr[0];// user_name
		objArr[1] = paramsArr[1];// time
		objArr[2] = paramsArr[2];// addr
		objArr[3] = paramsArr[3];// lat
		objArr[4] = paramsArr[4];// lng
		for (int i = 0; i < objArr.length; i++) {
			System.out.println(objArr[i] + "#");
		}

		String clientResult = "";
		try {
			clientResult = new WebServiceClient().invokeBDM(
					MAP_PHONE_SUBMITDATA, objArr);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("***********" + clientResult);

		writeJson(clientResult, resp);
	}

	private void map_phone_checkuser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("mobile_servlet------------>map_phone_checkuser");
		String params = CommonUtil.getNotNullStr(req.getParameter("params"));
		System.out.println("map手机端的参数：" + params);
		String[] paramsArr = params.split(",");
		Object[] objArr = new Object[2];
		objArr[0] = paramsArr[0];// user_name
		objArr[1] = paramsArr[1];// password
		for (int i = 0; i < objArr.length; i++) {
			System.out.println(objArr[i] + "#");
		}

		String clientResult = "";
		try {
			clientResult = new WebServiceClient().invokeBDM(
					MAP_PHONE_CHECKUSER, objArr);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("***********" + clientResult);

		writeJson(clientResult, resp);
	}

	@SuppressWarnings("deprecation")
	private void checkUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String params = CommonUtil.getNotNullStr(req.getParameter("params"));
		System.out.println(params);

		AppLog log = new AppLog();
		log.setAction("checkupdate");
		log.setFrom_client_time(CommonUtil.getCurrentTime());

		String[] paramsArr = params.split(",");

		Object[] objArr = new Object[2];
		objArr[0] = paramsArr[0];
		objArr[1] = paramsArr[1];
		log.setFrom_client(params);

		HashMap<String, String> resultMap = new HashMap<String, String>();
		String resultJson = "";

		log.setTo_server_time(CommonUtil.getCurrentTime());
		log.setTo_server(params);

		try {

			Map<String, Object> msgMap = new HashMap<String, Object>();

			resultMap.put("code", "1");

			String vcode = "";
			String path = "";
			path = req.getRealPath("/") + "//checkUpdate//" + "version.txt";
			System.out.println("path " + path);
			vcode = readFileByLines(path);
			System.out.println("vcode " + vcode);

			msgMap.put("VERSION_ID", vcode);

			resultMap.put("msg", new JSONObject(msgMap).toString());

			resultJson = new JSONObject(resultMap).toString();
		} catch (Exception e) {
			e.printStackTrace();
			resultJson = CommonUtil.genErrorJson(); // 查询出错时生成出错信息json
			log.setTo_client(resultJson);
		}

		log.setTo_client_time(CommonUtil.getCurrentTime());

		log.writeDb();

		System.out.println(resultJson);

		writeJson(resultJson, resp);
	}

	private void writeJson(String json, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.flush();
	}
	
	public String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				return tempString;
				// line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return "";
	}
}
