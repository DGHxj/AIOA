<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>顶点登录页面</title>
	<link rel="stylesheet" type="text/css" href="${app}/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${app}/css/body.css"/>
</head>
<body>
<div class="container">
	<section id="content">
		<form action="/user/login" method="Post">
			<h1>登录</h1>
			<div>
				<input type="text" placeholder="用户名" required="" id="username" name="userName"/>
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="password" name="userPassword"/>
			</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
			 </div> 
			<div>				
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
			</div>
		</form>
		 <div class="button">
			<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>	
		</div> <!-- button -->
	</section><!-- content -->
</div>
<!-- container -->
<br><br><br><br>

</body>
</html>