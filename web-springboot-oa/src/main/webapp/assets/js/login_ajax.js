
$(function(){
	
	//给form表单添加submit事件
	$("form").submit(function(){
		return login();
	});
	
});
function login(){
	//获取页面数据
	var userName=$("form input[name=username]").val();
	var userPassword=$("form input[name=password]").val();

	if(userName==""){
		$("form table tr:eq(0) td span").html("用户名不能为空");
		return false;
	}
	if(userPassword==""){
		$("form table tr:eq(1) td span").html("密码不能为空");
		return false;
	}
	//发送异步请求
	$.ajax({
		url:"/user_ajax/login",
		type:"get",
		data:{"userName":userName,"userPassword":userPassword},
		dataType:"json",
		success:function(result){
			//result是服务端返回的数据
			if(result.status==1){
				//window.location.href="index.html";
				window.location.href="/";

			}else if(result.status==0){
				alert(result.msg);
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
	
	return false;
}