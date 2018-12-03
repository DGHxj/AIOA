<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="icon"
	href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/face.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/jquery.webcam.min.js"></script>
<script src="assets/js/bootstrap.js"></script>
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<style type="text/css">
#photos {
	width: 320px;
	height: 240px;
	float: right;
}

#webcam {
	width: 320px;
	height: 240px;
	float: right;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#userPhone").blur(function() {
			var phone = $("#userPhone").val();
			if ($.trim(phone) == "") {
				alert("电话号码不能为空！");
				return;
			}
			var r=/^1[34578]\d{9}$/;
			if (!(r.test(phone))) {
				alert("电话号码有误！");
			}
		});
		$("#userEmail").blur(function() {
			var email = $("#userEmail").val();
			if ($.trim(email) == "") {
				alert("邮箱不能为空！");
				return;
			}
			var reg = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if (!(reg.test(email))) {
				alert("邮箱格式有误！");
			}
		});
	});
</script>

</head>
<body>
	<div class="admin-content" align="center">
		<div style="float: left;width: 300px">
			<div style="margin-left: 10px">
				<form class="am-form am-form-horizontal">
					<div class="am-form-group" style="width: 300px; text-align: left">
						<label for="user-name" class="am-form-label">姓名/Name:&nbsp;<font
							color="blue" size="10px">${sessionScope.user.userName }</font></label>
					</div>
					<div class="am-form-group" style="width: 300px; text-align: left">
						<label for="user-email" class="am-form-label">电子邮件/Email</label>
						<div>
							<input type="text" id="userEmail">
							<small>邮箱你懂得...</small>
						</div>
					</div>
					<div class="am-form-group" style="width: 300px; text-align: left">
						<label for="user-phone" class="am-form-label">电话/Telephone</label>
						<div>
							<input type="text" id="userPhone"><small>工作需要哦...</small>
						</div>
					</div>
					<input type="hidden" id="userId"
						value="${sessionScope.user.userId }">
				</form>
			</div>
		</div>
		<div style="float: right;margin-top:10px;width: 200px;">
			<div style="margin-right: 10px">
				<div id="webcam"></div>

				<div id="photos">
					<img src="" id="img">
				</div>
				<br> <input type="button" value="删除" class="btn btn-danger"
					id="delBtn" onclick="delPhoto();" style="margin-top: 10px" /> <input
					type="button" value="拍照" class="btn btn-info" id="saveBtn"
					onclick="savePhoto();" style="margin-top: 10px" />
			</div>
		</div>
		<div style="margin-top: 380px; margin-left: 200px" align="left">
			<button class="btn btn-primary btn-lg" onclick="saveUser()">注&nbsp;&nbsp;&nbsp;册</button>
		</div>
	</div>
	<!-- content end -->
</body>
</html>