﻿<%@page import="com.sh.manage.constants.SessionConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>控制台-Bootstrap后台管理系统</title>
		<meta name="keywords" content="Bootstrap模版" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="<%=path %>/static/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path %>/static/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=path %>/static/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="<%=path %>/static/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=path %>/static/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=path %>/static/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=path %>/static/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="<%=path %>/static/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<%=path %>/static/assets/js/html5shiv.js"></script>
		<script src="<%=path %>/static/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							H5后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div>
			<p style="text-indent: 2em; margin-top: 30px;">
			您要访问的页面，正在开发中... 请稍等!
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				系统将在 <span id="time">5</span> 秒钟后自动跳转至首页，如果未能跳转，<a href="<%=path%>/unite/index.do" title="点击访问">请点击</a>。</p>
			<script type="text/javascript">  
			    delayURL();    
			    function delayURL() { 
			        var delay = document.getElementById("time").innerHTML;
			 var t = setTimeout("delayURL()", 1000);
			        if (delay > 0) {
			            delay--;
			            document.getElementById("time").innerHTML = delay;
			        } else {
			     clearTimeout(t); 
			            window.location.href = '<%=path%>/'+"unite/index.do";
			        }        
			    } 
			</script>
			<script type="text/javascript">

				if (top.location != self.location) {top.location=self.location;}
				
		    </script>			
		</div>
</body>
</html>

