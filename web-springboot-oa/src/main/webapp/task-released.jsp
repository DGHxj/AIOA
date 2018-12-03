<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze后台管理系统模板HTML 表格页面 - cssmoban</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="/assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="/assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="/assets/css/admin.css">
  <script type="text/javascript">
  	
  </script>
</head>
<body>
  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-xs">我发布的任务</div>
    </div>
    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
            <tr>
            	<th colspan="7" style="color:red;text-align:center;">${msg }</th>
            </tr>
              <tr>
                <th class="table-id">ID</th>
                <th class="table-title">任务内容</th>
                <th class="table-date">发布时间</th>
                <th class="table-date">提交时间</th>
                <th class="table-author">负责人</th>
                <th>任务情况</th>
                <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${taskList}"  var="taskItem" >
					<tr>
						<td>${taskItem.taskId }</td>
						<td><small>${taskItem.taskContent}</small></td>
						<td>${taskItem.taskCreateTime }</td>
						<td>${taskItem.taskFinishTime }</td>
						<td>${taskItem.user.userName }</td>
						<c:if test="${taskItem.taskStatus ==2}"><td>未接受</td></c:if>
						<c:if test="${taskItem.taskStatus ==3}"><td style="color:red">已拒绝</td></c:if>
						<c:if test="${taskItem.taskStatus !=2}">
						<c:if test="${taskItem.taskStatus !=3}">
								<td style="color:lightgreen">已接受</td></c:if></c:if>
								
						<td><a href="/task/changeTask?taskId=${taskItem.taskId}&
						taskContent=${taskItem.taskContent}&taskStatus=${taskItem.taskStatus}&
						taskEndTime=${taskItem.taskEndTime}"  
						class="am-btn am-btn-default am-btn-xs am-text-secondary">修改</a>
						<a href="javascript:if(confirm('你确定要删除该任务吗？')) location='/task/deleteTask/${taskItem.taskId }/${taskItem.taskStatus }'" class="am-btn am-btn-default am-btn-xs am-text-danger">删除</a></td>
						
					</tr>
				</c:forEach>
            <!-- <tr>
              <td>1</td>
              <td><small>任务内容任务内容任务内容任务内容任务内容任务内容任务内容任务内容任务内容</small></td>
              <td>2014年9月4日 7:28:47</td>
              <td>2014年9月4日 7:28:47</td>
              <td>测试1号</td>
              
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</button>
                    <button class="am-btn am-btn-default am-btn-xs am-text-danger"><span class="am-icon-trash-o"></span> 删除</button>
                  </div>
                </div>
              </td>
            </tr> -->
          </tbody>
        </table>
          <div class="am-cf">
  共${taskTotal }条记录
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">«</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
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
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="/assets/js/app.js"></script>
</body>
</html>
