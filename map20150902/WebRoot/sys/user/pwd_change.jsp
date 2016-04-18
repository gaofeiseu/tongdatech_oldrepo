<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>


		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
		 function doSubmit(){	    
		    fmSubmit("#fm","user_savePWD",undefined,
		        function(result){
		        });
		 }
		</script>

	</head>

	<body>
		<xl:wrap title="密码修改">
			<form id="fm" method="post"
			style="margin: 50px auto;width:600px">
			<fieldset>
              <legend>密码修改</legend>
			<div class="form_item col1">
				<label>输入旧密码:</label>
				<input type="password" name="old_pwd" class="easyui-validatebox"
					data-options="required:true" autocomplete="off" />
		    </div>
		    <div class="form_item col1">
				<label>输入新密码:</label>
				<input type="password" name="password" id="password" class="easyui-validatebox"
					data-options="required:true,validType:'equalTo[\'#repeat_pwd\']'" autocomplete="off" />
			</div>
			<div class="form_item col1">
			    <label>确认新密码:</label>
				<input type="password" name="repeat_pwd" id="repeat_pwd" class="easyui-validatebox"
					data-options="required:true,validType:'equalTo[\'#password\']'" autocomplete="off" />
			</div>
			
			
			<div class="form_item col1" style="text-align: center;">
				
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="doSubmit()">确认</a>
				
			</div>
			</fieldset>
			</form>
			
		</xl:wrap>
	</body>
</html>
