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
		function runJS(){
		   var src=$("#src").val();
		   try{
		      var rs=eval(src);
		      $("rs").html(rs);
		   }catch (e) {
			 $("rs").html(e);
		}
		
		}
		</script>


	</head>

	<body>
		<xl:wrap title="js���� ">
		
			<fieldset>
              <legend>��ϵͳ���������ɵĿؼ�</legend>
			<div class="form_item col1"><!--col2 3�в���  -->
				<label>JS����</label>
				<textarea rows="6" cols="20" id="src"></textarea>
				
		    </div>
		    <div class="form_item col1">
		       <div id="rs"></div>
		    </div>
            <div class="form_item col1">
                <a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="runJS()">����</a>
		    </div>
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
