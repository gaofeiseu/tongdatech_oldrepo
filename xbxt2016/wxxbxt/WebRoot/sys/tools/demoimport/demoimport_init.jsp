<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>


<html>



<head>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<xl:resource></xl:resource>
	<script type="text/javascript">
		function confrim()
		{
			var strFileFormat = fm.datafile.value.match(/^(.*)(\.)(.{1,8})$/)[3];
			if(strFileFormat== "xls"||strFileFormat== "csv"||strFileFormat== "dbf")
			{
				var xls = document.getElementById("div_xls");
				var csv = document.getElementById("div_csv");
				var dbf = document.getElementById("div_dbf");
				if(strFileFormat=="xls")
				{
					xls.style.display="";
					csv.style.display="none";
					dbf.style.display="none";
					document.getElementById("type").value="1";
				}
			
				if(strFileFormat=="csv")
				{
					xls.style.display="none";
					csv.style.display="";
					dbf.style.display="none";
					document.getElementById("type").value="2";
				}
			
				if(strFileFormat=="dbf")
				{
					xls.style.display="none";
					csv.style.display="none";
					dbf.style.display="";
					document.getElementById("type").value="3";
				}
				return true; 
			}
			
			
			else 
			{ 
				$.messager.alert("失败","只能选择.xls,.csv,.dbf文件！");
				return false; 
			} 
		}
		
		function next()
		{
			doNext("#fm");
		}
		
		function doNext(selector) 
		{
			$(selector).submit();
		}
	

	</script>

</head>
	<body>
	<form id="fm" action="import_upload" method="post"
				enctype="multipart/form-data">
		<input type="hidden" name="type" id="type" value="1"/>
			<div>
		<fieldset>
			<legend> 请选择文件 </legend>
			<label>	文件选择: </label>
			<span> 
			<span class="file_input">
			<input type="text" name="file_text" id="file_text" class="file_show"  readonly="readonly">
			&nbsp;
			<a style="height:20px;width:20px" class="file_link"> 
				<input type="file" class="file_real"  id="datafile"   
				name="datafile" style="height:20px;width:120px;margin-left: -100px" 
 				onchange="confrim(),file_text.value=value" >
				</a>
			</span>
			<%--input type="file" name="datafile" id="datafile" 
			style="position:relative;left:0;top:0;
			z-index:999;opacity:0;filter:alpha(opacity=20); " 
			onchange="confrim(),ye.value=value">
			<input type="text" name=ye 
				style="color:green; border:1px solid green;position:absolute;left:70px;top:36px;"> 
			<input type="button" value="open"
				style="position:absolute;left:220px;top:35px;"--%>
			</span>	
		</fieldset>
				</div>
	
	  <div id="div_xls" style="display:none">
		<div class="easyui-panel" title="import xls" data-options="fit:true,plain:true">
			
				
				
          		<div class="form_item">
					<label>
						起始行数:
					</label>
					<input type="text" name="xls_st_row" />
				</div>
				<div class="form_item">
					<label>
						结束行数:
					</label>
				<input type="text"  name="xls_ed_row" />
				</div>	
				<div class="form_item" style="text-align:right">
					<a href="###" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-next'"
						onclick="next()">下一步</a>
				</div>
			
		</div>
	</div>	
	
	<div id="div_csv" style="display:none">
		<div class="easyui-panel" title="import csv" data-options="fit:true,plain:true">
			
          		<div class="form_item">
					<label>
						起始行数:
					</label>
					<input type="text" name="csv_st_row" />
				</div>
				<div class="form_item">
					<label>
						结束行数:
					</label>
				<input type="text"  name="csv_ed_row" />
				</div>	
				<div class="form_item">
					<label>分隔符:</label>
					<select class="easyui-combobox" name="delimiter" style="width:153px;required:true">
					<option value=','>,</option>
					<option value='|'>|</option>
					</select>
					
				</div>
				
			<div class="form_item" style="text-align:right">
					<a href="###" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-next'"
						onclick="next()">下一步</a>
				</div>
		</div>
	</div>	
	
	<div id="div_dbf" style="display:none">
		<div class="easyui-panel" title="import dbf" data-options="fit:true,plain:true">
			
          		<div class="form_item">
					<label>
						起始行数:
					</label>
					<input type="text" name="dbf_st_row" />
				</div>
				<div class="form_item">
					<label>
						结束行数:
					</label>
				<input type="text"  name="dbf_ed_row" />
				</div>	
			<div class="form_item col1" style="text-align:right">
					<a  href="###" class="easyui-linkbutton"
						data-options="plain:true,iconCls:'icon-next'"
						onclick="next()">下一步</a>
				</div>
		</div>
	</div>	
	
	
</form>


	</body>

</html>