<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>顶点oa管理</title>
<meta name="description" content="这是一个 user 页面">
<meta name="keywords" content="user">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<style type="text/css">
.logout:HOVER {
	cursor: pointer;
}
</style>
</head>
<body>


	<div class="am-cf admin-header">
		<!-- sidebar start -->
		<div class="admin-sidebar">
			<ul class="am-list admin-sidebar-list">
				<!-- 签到系统 -->
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav1'}"><span
						class="am-icon-file"></span> 签到管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub"
						id="collapse-nav1">
						<li><a target="right" href="admin-index"><span
								class="am-icon-table"></span> 人脸签到</a></li>
						<li><a target="right" href="face-book" class="am-cf"><span
								class="am-icon-pencil-square-o"></span> 人脸注册<span
								class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
					</ul></li>
				<!-- 任务系统 -->
				<li class="admin-parent"><a class="am-cf"
					data-am-collapse="{target: '#collapse-nav'}"><span
						class="am-icon-file"></span> 任务管理 <span
						class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub" id="collapse-nav">
						<c:if test="${sessionScope.user.roleId ne '3'}">
							<c:forEach items="${sessionScope.map.get('任务管理') }" var="item">
								<li><a target="right" href="${item.popedomUrl }"
									class="am-cf"><span class="am-icon-pencil-square-o"></span>${item.popedomName }<span
										class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
							</c:forEach>
						</c:if>
						<li><a target="right" href="/task-search.jsp"><span
								class="am-icon-table""></span> 搜索任务</a></li>
						<li><a target="right" href="/task/showTasks/1"><span
								class="am-icon-table""></span> 待完成</a></li>
						<li><a target="right" href="/task/showTasks/2"><span
								class="am-icon-table"></span> 待接收</a></li>
						<li><a target="right" href="/task/showTasks/3"><span
								class="am-icon-bug""></span> 已拒绝</a></li>
						<li><a target="right" href="/task/showTasks/4"><span
								class="am-icon-check"></span> 已完成</a></li>
					</ul></li>

				<!-- 权限管理 -->
				<c:if test="${sessionScope.user.roleId=='0'}">
					<li class="admin-parent"><a class="am-cf"
						data-am-collapse="{target: '#collapse-nav2'}"><span
							class="am-icon-file"></span> 权限管理 <span
							class="am-icon-angle-right am-fr am-margin-right"></span></a>
						<ul class="am-list am-collapse admin-sidebar-sub"
							id="collapse-nav2">
							<li><a target="right" href="user-add.jsp" class="am-cf"><span
									class="am-icon-pencil-square-o"></span> 添加新用户<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
									<li><a target="right" href="/popedom/rolePopeList"><span
									class="am-icon-table""></span> 权限列表</a></li>
									<li><a target="right" href="popedom-add" class="am-cf"><span
									class="am-icon-pencil-square-o"></span> 添加权限<span
									class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
						</ul></li>
				</c:if>
				<li class="logout"><a "javascript:void(0);" onclick="logout()"><span
						class="am-icon-sign-out"></span> 注销</a></li>
			</ul>

			<div class="am-panel am-panel-default admin-sidebar-panel">
				<div class="am-panel-bd">
					<p>
						<span class="am-icon-bookmark"></span> 公告
					</p>
					<p>越努力，越幸运！！！</p>
				</div>
			</div>
			<div class="am-panel am-panel-default admin-sidebar-panel">
				<div class="am-panel-bd">
					<p>
						<span class="am-icon-tag"></span> wiki
					</p>					
					<p>Welcome to 顶点oa系统</p>
				</div>
			</div>
		</div>
		<!-- sidebar end -->
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<!--<![endif]-->
	<script src="assets/js/app.js"></script>
	<script type="text/javascript">
		function logout() {

			$.ajax({
				url : "/user/logout",
				success : function() {
					parent.location.reload();
				}
			});
		}
	</script>

</body>
</html>
