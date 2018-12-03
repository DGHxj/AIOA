<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>oa权限添加</title>
  <meta name="description" content="这是一个form页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="${app}/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${app}/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="${app}/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${app}/assets/css/admin.css">
</head>
<body>
<!-- content start -->
<div class="admin-content">

  <div class="am-cf am-padding">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">权限管理</strong> / <small>角色权限添加</small></div>
  </div>

  <div class="am-tabs am-margin" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="am-active"><a href="#tab1">权限信息</a></li>
    </ul>
    <form action="/popedom/addRolePope" method="post">
     <div class="am-tabs-bd">
      <div class="am-tab-panel am-fade am-in am-active" id="tab1">
        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right">角色名称</div>
          <div class="am-u-sm-10">
            <select name="roleName">
              <option value="boss">boss</option>
              <option value="组长">组长</option>
              <option value="成员">成员</option>
            </select>
          </div>
        </div>
        <div class="am-g am-margin-top">
          <div class="am-u-sm-2 am-text-right">权限名称</div>
          <div class="am-u-sm-10">
            <label><input name="popedomName" type="checkbox" value="发布任务" />&nbsp;<small>发布任务</small> </label>&nbsp;&nbsp; &nbsp; &nbsp;  
            <label><input name="popedomName" type="checkbox" value="我发布的" />&nbsp;<small>我发布的任务 </small></label> 
          </div>
        </div>
        
        <div class="am-margin">
    		<input type="submit" class="am-btn am-btn-primary am-btn-xs"></input>
    		<input type="reset" class="am-btn am-btn-primary am-btn-xs"></input>
  		</div>
    </form>
    
      </div>
    </div>
  </div>

  
</div>
<!-- content end -->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${app}/assets/js/jquery.min.js"></script>
<script src="${app}/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${app}/assets/js/app.js"></script>
</body>
</html>
