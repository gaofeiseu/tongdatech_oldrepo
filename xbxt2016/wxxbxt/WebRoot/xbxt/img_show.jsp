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
		<title>图片显示</title>
		<xl:resource></xl:resource>
		<script type="text/javascript">
		var winWidth;
		var winHeight;
		// 获取窗口宽度
		if (window.innerWidth){
			winWidth = window.innerWidth;
		}else if ((document.body) && (document.body.clientWidth)){
			winWidth = document.body.clientWidth;
		}
		// 获取窗口高度
		if (window.innerHeight){
			winHeight = window.innerHeight;
		}else if ((document.body) && (document.body.clientHeight)){
			winHeight = document.body.clientHeight;
		}
		// 通过深入 Document 内部对 body 进行检测，获取窗口大小
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