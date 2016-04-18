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
		var query_url = "/faq_query.action";
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
			if(value&&value.length>200){
				return value.substring(0,200)+"...";
			}else{
				return value;
			}
		}
		function addQuestion(){
			$("#question_dlg").dialog("open").dialog("setTitle","新增问题");
			$("#question_fm").form("clear");
			
		}
		function editQuestion(){
			var row = $("#questionTab").datagrid("getSelected");
			if(row){
				if(row.question_status=='1'){
					$("#question_id").val(row.question_id);
					$("#question_title").val(row.question_title);
					$("#question_content").val(row.question_content);
					$("#question_dlg").dialog("open").dialog("setTitle","修改问题");
				}else{
					$.messager.alert('请注意','只能对处于【提交状态】的问题进行修改操作！');
				}
			}else{
				$.messager.alert('请注意','请先选择要修改的问题！');
			}
		}
		function deleteQuestion(){
			var row = $("#questionTab").datagrid("getSelected");
			if(row){
				if(row.question_status=='1'){
					$.messager.confirm('确认', '确认删除这条问题内容？', function(r){
		                if (r){
		                	var url = "/faq_deleteQuestion.action";
		    				var data = {};
		    				data["question_id"] = row.question_id;
		    				$.ajax({
		    					type : "POST",
		    					cache : false,
		    					async : true,
		    					url : url,
		    					dataType : "json",
		    					data : data,
		    					success : function(result) {
		    						if(result.success){
		    							$.messager.show({title: "成功",msg: result.msg});
		    						}else{
		    							$.messager.show({title: "错误",msg: result.msg});
		    						}
		    						$("#questionTab").datagrid("reload");
		    					},
		    					error : function(msg) {
		    						alert('error!!');
		    					}
		    				});
		                }
		            });
				}else{
					$.messager.alert('请注意','只能对处于【提交状态】的问题进行删除操作！');
				}
			}else{
				$.messager.alert('请注意','请先选择要删除的问题！');
			}
		}
		function save_question(){
			fmSubmit("#question_fm","/faq_saveQuestion.action",function(result){
				if(result.success){
					$("#questionTab").datagrid("reload");
					$("#question_dlg").dialog("close");
				}
			});
		}
		</script>
	</head>
	<body class="easyui-layout">
		<div id="toolbar" style="padding: 5px; display: none">
			<form id="fm" style="margin: 0">
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addQuestion();">新增问题</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"  onclick="editQuestion();">修改问题</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'" onclick="deleteQuestion();">删除问题</a>
			</form>
		</div>
		<div data-options="region:'center' ,title:'问题报障与回复查看',split:true" style="background:#fafafa;overflow:hidden;" >
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
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="question_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{ text:'保存',plain:true,iconCls:'icon-ok',handler:function(){save_question();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',handler:function(){$('#question_dlg').dialog('close');}}]"
				style="width:430px;height:260px;padding:10px 10px;">
			<form id="question_fm" method="post">
				<input type="hidden"   id="question_id" name="question_id" value="0"/>
				<div class="form_item col1">
				   <label>问题标题：</label>
				   <input type="text" id="question_title" name="question_title" class="easyui-validatebox" style="width:250px" data-options="required:true" />
			  	</div>
			  	<div class="form_item col1">
				   <label>问题内容：</label>
				   <textarea id="question_content" name="question_content" rows="6" cols="33">
			  	</div>
			</form>
		</div>
		
	</body>
</html>