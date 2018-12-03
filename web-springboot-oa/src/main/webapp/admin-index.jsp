<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<link rel="icon"
	href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/data.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/series-label.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/oldie.js"></script>
<script
	src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/face2.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/jquery.webcam.min.js"></script>
<script src="assets/js/bootstrap.js"></script>
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<script src="/assets/js/tu.js"></script>
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

</head>
<body>
	<div id="container" style="min-width: 200px; height: 400px"></div>
	<div class="message"></div>
	<div align="center">
		<!-- 按钮触发模态框 -->
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal2">人脸签到</button>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 400px; height: 700px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>

						<div class="am-cf am-padding">
							<div class="am-fl am-cf">
								<h4 class="modal-title" id="myModalLabel">
									<strong class="am-text-primary am-text-lg">人脸签到</strong> / <small>Face
										Sign</small>
								</h4>
							</div>
						</div>

					</div>
					<!-- content start -->
					<div class="admin-content" align="center">
						<div style="margin-top: 20px; text-align: center;">
							<div id="webcam" style="margin-right: 35px"></div>
							<input type="button" value="拍照签到"
								class="btn btn-info  btn-primary btn-lg" id="saveBtn"
								onclick="savePhoto();"
								style="margin-bottom: 10px; margin-top: 5px" /> 
						</div>

					</div>
					<!-- content end -->

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>