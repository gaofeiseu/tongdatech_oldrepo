<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<title>ͼƬ��ʾ</title>
		<xl:resource></xl:resource>
		<script type="text/javascript">
		var winWidth;
		var winHeight;
		// ��ȡ���ڿ��
		if (window.innerWidth){
			winWidth = window.innerWidth;
		}else if ((document.body) && (document.body.clientWidth)){
			winWidth = document.body.clientWidth;
		}
		// ��ȡ���ڸ߶�
		if (window.innerHeight){
			winHeight = window.innerHeight;
		}else if ((document.body) && (document.body.clientHeight)){
			winHeight = document.body.clientHeight;
		}
		// ͨ������ Document �ڲ��� body ���м�⣬��ȡ���ڴ�С
		if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
		{
			winHeight = document.documentElement.clientHeight;
			winWidth = document.documentElement.clientWidth;
		}
		$(function(){
			$(".show_img").css("height",winHeight+"px");
		});
		</script>
	</head>
	<body>
		<img class="show_img" alt="" src="/xbmobile_file/<s:property value="bean.img_path"/>">
	</body>
</html>