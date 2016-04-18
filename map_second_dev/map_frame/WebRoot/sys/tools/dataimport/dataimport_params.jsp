<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>

<!DOCTYPE HTML>
<html>
	<head>


		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<xl:resource></xl:resource>


	</head>
	<script type="text/javascript">
	function excelNext(){
	   doNext("#fm_excel");
	}
	
	function csvNext(){
	   doNext("#fm_csv");
	}
	
	function dbfNext(){
		doNext("#fm_dbf");
	}
	
	function doNext(selector) {
		$(selector).submit();
	}
	
	function xls_confrim()
	{
		var strFileFormat = fm_excel.datafile.value.match(/^(.*)(\.)(.{1,8})$/)[3];
		if(strFileFormat!="xls")
		{
			$.messager.alert("失败","只能选择xls文件！");
			return false; 
		}
	}
		
</script>

	<body>

		<div id="tt" class="easyui-tabs" data-options="fit:true,plain:true">
			<s:iterator value="context.config.types" id="types" status="st">

				<s:if test="#types=='excel'">
					<div title="EXCEL导入" style="padding: 20px;">
						<form id="fm_excel" action="/import_upload" method="post"
							enctype="multipart/form-data">
							<fieldset>
								<legend>
									${context.config.title}
								</legend>
							
								<div class="form_item col1">
									<label>
										上传EXCEL:
									</label>
									<span> <xl:file name="datafile" accept="application/vnd.ms-excel" onchange="xls_confrim()"></xl:file> </span>
									<input type="hidden" name="type" value="1"/>
								</div>
								
								<s:if test="context.config.params">
                                <div class="form_item col2">
									<label>
										起始行数:
									</label>
									<input type="text" name="xls_st_row" />
								</div>
								<div class="form_item col2">
									<label>
										结束行数:
									</label>
									<input type="text"  name="xls_ed_row" />
								</div>
								</s:if>
								<div class="form_item col1" style="text-align: center">
									<a href="###" class="easyui-linkbutton"
										data-options="plain:true,iconCls:'icon-next'"
										onclick="excelNext()">下一步</a>

								</div>
							</fieldset>
						</form>
					</div>
				</s:if>
				<s:if test="#types=='csv'">
					<div title="CSV导入" style="padding: 20px;">
						<form id="fm_csv" action="import_upload" method="post"
							enctype="multipart/form-data">
							<fieldset>
								<legend>
									${context.config.title}
								</legend><div class="form_item col2">
									<label>
										上传CSV:
									</label>
									<span> <xl:file name="datafile" accept="application/vnd.ms-excel"></xl:file> </span>
									<input type="hidden" name="type" value="2"/>
								</div>
								
								<div class="form_item col2">
								<label>请选择分隔符:</label>
								<select name="delimiter" style="width:153px;required:true">
								<option value=','>,</option>
								<option value='|'>|</option>
								</select>
								</div>
								
								<s:if test="context.config.params">
                                <div class="form_item col2">
									<label>
										起始行数:
									</label>
									<input type="text" name="csv_st_row" />
								</div>
								<div class="form_item col2">
									<label>
										结束行数:
									</label>
									<input type="text"  name="csv_ed_row" />
									<br>
									<br>
								</div>
								</s:if>
								<div class="form_item col1" style="text-align: center">
									<a href="###" class="easyui-linkbutton"
										data-options="plain:true,iconCls:'icon-next'"
										onclick="csvNext()">下一步</a>

								</div>
								</fieldset>
						</form>
					</div>
				</s:if>
				<s:if test="#types=='dbf'">
					<div title="DBF 导入" style="padding: 20px;">
						<form id="fm_dbf" action="/import_upload" method="post"
							enctype="multipart/form-data">
							<fieldset>
								<legend>
									${context.config.title}
								</legend>
							
								<div class="form_item col1">
									<label>
										上传dbf:
									</label>
									<span> <xl:file name="datafile" accept="application/vnd.ms-excel"></xl:file> </span>
									<input type="hidden" name="type" value="3"/>
								</div>
								
								<s:if test="context.config.params">
                                <div class="form_item col2">
									<label>
										起始行数:
									</label>
									<input type="text" name="dbf_st_row" />
								</div>
								<div class="form_item col2">
									<label>
										结束行数:
									</label>
									<input type="text"  name="dbf_ed_row" />
								</div>
								</s:if>
								<div class="form_item col1" style="text-align: center">
									<a href="###" class="easyui-linkbutton"
										data-options="plain:true,iconCls:'icon-next'"
										onclick="dbfNext()">下一步</a>

								</div>
							</fieldset>
						</form>
					</div>
				</s:if>
			</s:iterator>
		</div>

	</body>
</html>
