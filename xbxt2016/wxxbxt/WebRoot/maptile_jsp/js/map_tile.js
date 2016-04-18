/**
 * @author Mr.GaoFei
 */
function subimit_frm(){
	var select_folder = '';
	select_folder = $("#select_folder").val();
	if(!select_folder){
		$.messager.show({title: "错误",msg: "文件夹路径不能为空！！"});
		return;
	}
	$('#subimit_btn').linkbutton('disable');
	fmSubmit("#frm","map_tile_doop.action",function(result){
		$('#subimit_btn').linkbutton('enable');
	});
}

function subimit_frm2(){
	var Zoom = '';
	Zoom = $("#select_Zoom").val();
	if(!Zoom){
		$.messager.show({title: "错误",msg: "Zoom不能为空！！"+Zoom});
		return;
	}
	var select_X = '';
	select_X = $("#select_X").val();
	if(!select_X){
		$.messager.show({title: "错误",msg: "select_X类型不能为空！！"});
		return;
	}
	var select_Y = '';
	select_Y = $("#select_Y").val();
	if(!select_Y){
		$.messager.show({title: "错误",msg: "select_Y不能为空！！"});
		return;
	}
	$('#subimit_btn2').linkbutton('disable');
	fmSubmit("#frm2","map_tile_foop.action",function(result){
		$('#subimit_btn2').linkbutton('enable');
	});
}

function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
//				$("#zip_src").attr("href",result.returnString);
//				$("#hiddendiv1").show();
				$.messager.show({title: "成功",msg: result.msg});
			}else{
				//
				$.messager.show({title: "失败",msg: result.msg});
			}
			callback(result);
		},
		error: function(){
			alert('error!!');
		}
	});
}