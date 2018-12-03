window.onload=function() {
	$("select[name=roleName]").change(
			function() {
				var roleName = $(this).val();
				var num=0;
				$.ajax({
					url:"/user_ajax/selectMajor",
					type:"post",
					data:{"roleName":roleName},
					dataType:"json",
					success:function(data){
						$('#major').empty();
						$('<option value="0">选择角色</option>').appendTo($("#major"));
						for(var i= 0;i<data.length;i++){
							$("#major").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
						}
						 // 缺一不可  
//				         $('#major').selectpicker('refresh');  	
//				         $('#major').selectpicker('render');
					},
					error:function(){
						alert("请求失败！");
					}
				});
				/*if (!formObj.checkNull("roleName", "用户名不能为空")) {
					setMsg("roleName", "角色名称不能为空");
				} else {
					
				}*/
			});
	//给注册表单注册submit事件
//	$("form").submit(function(){
//		return register();
//	});
}
function register(){
	var userName=$("form input[name=userName]").val();
	var userPassword=$("form input[name=userPassword]").val();
	var roleName=$("form input[name=roleName]").val();
	var major=$("form input[name=major]").val();
	var flag=formObj.checkForm();
	if(flag){
		$.ajax({
			url:"/user_ajax/regist",
			type:"post",
			data:{
				  "userName":userName,
				  "userPassword":userPassword,
				 },
			dataType:"json",
			success:function(result){
				if(result.status==1){
					alert(result.message);
					window.location.href="/page/login";
				}else if(result.status==0){
					alert(result.message);
				}
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
	
	return false;
}
var formObj = {
	checkForm : function() {
		var flag = true;
		// 非空验证
		flag = this.checkNull("userName", "用户名不能为空!");
		flag = this.checkNull("userPassword", "密码不能为空") && flag;
		flag = this.checkNull("roleName", "确认密码不能为空") && flag;
		flag = this.checkNull("major", "昵称不能为空") && flag;
		return flag;
	},
	checkNull : function(name, msg) {
		var value = $("input[name=" + name + "]").val();
		if ($.trim(value) == "") {
			this.setMsg(name, msg);
			return false;
		}
		return true;
	},
	setMsg : function(name, msg) {
		$("#" + name + "_msg").text(msg);
	}
};