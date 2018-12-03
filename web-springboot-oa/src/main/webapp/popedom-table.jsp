<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>oa角色权限表</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
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
      <div class="am-fl am-cf"><strong class="am-text-xs">权限列表</div>
    </div>
    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-title">角色名</th>
                <th class="table-title">权限名</th>
                <th class="table-author">请求URL</th>
                <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
            <c:forEach items="${rpList }" var="rolePopedom">
            <tr>
              <td>${rolePopedom.roleName }</td>
              <td><small>${rolePopedom.popedomName }</small></td>
              <td>${rolePopedom.popedomUrl }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                   <a href="javascript:if(confirm('你确定要删除该任务吗？')) location='/popedom/deleteRolePope/${rolePopedom.roleName }/${rolePopedom.popedomName }'" 
                   class="am-btn am-btn-default am-btn-xs am-text-danger" id="del">删除</a>                 
                  </div>
                </div>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
          <div class="am-cf">
  共 15 条记录
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">«</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">»</a></li>
    </ul>
  </div>
</div>
          <hr />
          <p>注：.....</p>
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->
<script src="${app}/assets/js/jquery.min.js"></script>
<script src="${app}/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${app}/assets/js/app.js"></script>
</body>
</html>
