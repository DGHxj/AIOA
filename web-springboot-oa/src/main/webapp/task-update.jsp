<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Amaze后台管理系统模板HTML表单页面 - cssmoban</title>
<meta name="description" content="这是一个form页面">
<meta name="keywords" content="form">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="/assets/css/amazeui.min.css" />
<link rel="stylesheet" href="/assets/css/admin.css">
</head>
<body>
	<!-- content start -->
	<form action="/task/sendTask"
		method="post">
		<div class="admin-content">

			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">任务管理</strong> / <small>任务发布</small>
				</div>
			</div>
	
			<div class="am-tabs am-margin" data-am-tabs>
				<ul class="am-tabs-nav am-nav am-nav-tabs">
				
					<li class="am-active"><a href="#tab1">基本信息</a></li>
					
				</ul>
				
				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade am-in am-active" id="tab1">
						<div class="am-g am-margin-top">
							<div style="color:red;text-align:center">${msg}</div>
							<div class="am-u-sm-2 am-text-right">发布人</div>
							<div class="am-u-sm-10">
								<div>boss</div>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-2 am-text-right">接收人</div>
							<div class="am-u-sm-10">
								<select name="taskGetId">
									<option value="option1">选项一...</option>
									<option value="option2">选项二.....</option>
									<option value="option3">选项三........</option>
								</select>
							</div>
						</div>
						<div class="am-g am-margin-top">
							<div class="am-u-sm-2 am-text-right">截止时间</div>
							<div class="am-u-sm-10">
								<form action="" class="am-form am-form-inline">
									<div class="am-form-group am-form-icon">
										<i class="am-icon-calendar"></i> <input type="text"
											class="am-form-field am-input-sm" placeholder="年/月/日"
											name="taskEndTime" value="${param.taskEndTime }">
									</div>
								</form>
							</div>
							
							<input hidden="" name="taskId" value="${param.taskId }" />
							<input hidden="" name="taskStatus" value ="${param.taskStatus }"></textarea>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">任务详情</div>
							<div class="am-u-sm-10">
								<textarea rows="10" placeholder="请输入任务内容" name="taskContent" >${param.taskContent }</textarea>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="am-margin">
				<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
				<button type="reset" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
			</div>
		</div>
	</form>
	<!-- content end -->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script src="/assets/js/app.js"></script>
</body>
</html>
