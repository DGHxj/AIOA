<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>顶点oa管理</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
  <link rel="stylesheet" href="assets/css/bootstrap.css">
  <link rel="stylesheet" href="/assets/css/style.css">
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
	  <div class="row" style="margin-top: 100px">
	    <div class="col-xs-4"></div>
        <div class="col-xs-4">
          <h2 class="text-primary">任务信息搜索</h2>
        </div>
        <div class="col-xs-4"></div>
	  </div>
	  <form class="navbar-form navbar-center" role="search" action="/task/searchTask" method="post">
	   <div class="form-group">
	  <div class="row" style="margin-top: 50px">
		  <div class="col-xs-3"></div>
		  <div class="col-xs-6">
            <input type="text" class="form-control input-lg " placeholder="Search" name="search"> 				
		  </div>
		  <div class="col-xs-3"></div>
	  </div>
	   <div class="row" style="margin-top: 10px">
	    <div class="col-xs-5"></div>
	    <div class="col-xs-4">
	       <button type="submit" class="btn-success btn-lg">搜索一下</button>
	    </div>
	    <div class="col-xs-3"></div>
	   </div>
	  </div>
	  </form>
	</div>

</body>
</html>
