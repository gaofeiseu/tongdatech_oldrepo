<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
		var query_url = "/faq_queryForSearch.action";
		function clickRowFunction(rowIndex, rowData){
			
		}
		function formatQuestionTitle(value,row,index){
			if(value&&value.length>200){
				return value.substring(0,200)+"...";
			}else{
				return value;
			}
		}
		function formatReplyMsg(value,row,index){
			if(value&&value.length>2){
				return "<a href=\"javascript:showReplyMsgDetail('"+value+"','"+row.question_title+"','"+row.question_content+"');\">查看详情</a>";
			}else{
				return value;
			}
		}
		
		function showReplyMsgDetail(a,b,c){
			$("#question_title").val(b);
			$("#question_content").val(c);
			$("#question_reply_msg").val(a);
			$("#question_dlg").dialog("open").dialog("setTitle","问题详情");
		}
		
		function searchQuestion(){
			var queryDate =$("#fm").form2json();
			$("#questionTab").datagrid("load",queryDate);
		}
		</script>
	</head>
	<body class="easyui-layout">
		<div id="toolbar" style="padding: 5px; display: none">
			<form id="fm" style="margin: 0">
				问题搜索：<input type="text" id="search_question_str" name="search_question_str" />
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-tip'" onclick="searchQuestion();">查询</a>
			</form>
		</div>
		<div data-options="region:'center' ,title:'问题查询',split:true" style="background:#fafafa;overflow:hidden;" >
			<table id="questionTab" class="easyui-datagrid" data-options="
     			fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     			pageSize:10,singleSelect:true,url:query_url,border:false,
     			onClickRow:clickRowFunction" >
				<thead>
					<tr>
						<th data-options="field:'question_id',align:'center'" width="100">
							问题ID
						</th>
						<th data-options="field:'question_status_time',align:'center'" width="150">
							状态更新时间
						</th>
						<th data-options="field:'question_title',align:'center',formatter:formatQuestionTitle" width="300">
							问题标题
						</th>
						<th data-options="field:'question_status_str',align:'center'" width="150">
							问题状态
						</th>
						<th data-options="field:'question_reply_msg',align:'center',formatter:formatReplyMsg" width="300">
							问题回复
						</th>
						<th data-options="field:'user_name',align:'center'" width="80">
							用户名
						</th>
						<th data-options="field:'nick_name',align:'center'" width="80">
							别名
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="question_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{ text:'关闭',plain:true,iconCls:'icon-cancel',handler:function(){$('#question_dlg').dialog('close');}}]"
				style="width:430px;height:360px;padding:10px 10px;">
			<form id="question_fm" method="post">
				<div class="form_item col1">
				   <label>问题标题：</label>
				   <input type="text" id="question_title" name="question_title" class="easyui-validatebox" style="width:250px" data-options="required:true" readonly/>
			  	</div>
			  	<div class="form_item col1">
				   <label>问题内容：</label>
				   <textarea id="question_content" name="question_content" rows="6" cols="33" readonly></textarea>
			  	</div>
			  	<div class="form_item col1">
				   <label>问题回复：</label>
				   <textarea id="question_reply_msg" name="question_reply_msg" rows="6" cols="33" readonly></textarea>
			  	</div>
			</form>
		</div>
		
	</body>
</html>