<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
     <meta charset="UTF-8" />
    <title>后台登录主页</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
     <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <!-- GLOBAL STYLES -->
     <!-- PAGE LEVEL STYLES -->
     <link rel="stylesheet" href="<%=path %>/static/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=path %>/static/assets/css/login.css" />
    <link rel="stylesheet" href="<%=path %>/static/assets/plugins/magic/magic.css" />
     <!-- END PAGE LEVEL STYLES -->
   <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
    <!-- END HEAD -->

    <!-- BEGIN BODY -->
<body class="login-layout" >

   <!-- PAGE CONTENT --> 
    <div class="container">
    <div class="text-center">
        <%-- <img alt="Charisma Logo" src="<%=path %>/imgages/logo20.png" /> <span>Charisma</span> --%>
    </div>
    <div class="tab-content">
        <div id="login" class="tab-pane active">
            <form  id="loginForm" name="loginForm" method="post" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">
                    H5管理系统
                </p>
                <input type="text" placeholder="用户名" id="usercode" name="usercode" title="请输入用户名" class="form-control" />
                <input type="password" placeholder="密码" id="password" name="password" title="请输入密码" class="form-control" />
                <input type="text" id="rand" name="rand" maxlength="4" tabindex="2" AUTOCOMPLETE="off" id="rand" value=""
									style="width: 66px;display: inline;" title="请输入验证码" class="form-control"/> 
				<img style="cursor: pointer;margin:10px 66px 0 5px;height: 36px;width: 100px;" width="58" id="imgCode" height="21" onclick="changeImg();"/>
                <button class="btn text-muted text-center btn-success"
					onclick="dologin();" style="margin:10px 0 0 0;">
									<i class="icon-key"></i>登录</button>
            </form>
        </div>
        <div id="forgot" class="tab-pane">
            <form action="index.html" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">输入有效邮箱</p>
                <input type="email"  required="required" placeholder="邮箱"  class="form-control" />
                <br />
                <button class="btn text-muted text-center btn-success" type="submit">重设密码</button>
            </form>
        </div>
        <div id="signup" class="tab-pane">
            <form action="index.html" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">填写详细注册信息</p>
                 <input type="text" placeholder="First Name" class="form-control" />
                 <input type="text" placeholder="Last Name" class="form-control" />
                <input type="text" placeholder="Username" class="form-control" />
                <input type="email" placeholder="您的邮箱" class="form-control" />
                <input type="password" placeholder="密码" class="form-control" />
                <input type="password" placeholder="确认密码" class="form-control" />
                <button class="btn text-muted text-center btn-success" type="submit">注册</button>
            </form>
        </div>
    </div>
    <div class="text-center">
        <ul class="list-inline">
            <li><a class="text-muted" href="#login" data-toggle="tab">登录</a></li>
            <li><a class="text-muted" href="#forgot" data-toggle="tab">忘记密码</a></li>
            <!-- <li><a class="text-muted" href="#signup" data-toggle="tab">注册</a></li> -->
        </ul>
    </div>


</div>

	  <!--END PAGE CONTENT -->     
	      
      <!-- PAGE LEVEL SCRIPTS -->
      <script src="<%=path %>/static/assets/plugins/jquery-2.0.3.min.js"></script>
      <script src="<%=path %>/static/assets/plugins/bootstrap/js/bootstrap.js"></script>
   <script src="<%=path %>/static/assets/js/login.js"></script>
      <!--END PAGE LEVEL SCRIPTS -->

<script type="text/javascript" lang="javascript">
//body onload事件处理
$(function(){
	/**
	初始化数据
	*/
	$('#imgCode').attr("title","点击重新获得验证码!");
	$('#imgCode').attr("src","<spring:url value='/authImage' htmlEscape='true'/>");
});

function changeImg(){
	//修改为可以点击图片切换验证码
	document.getElementById("imgCode").setAttribute("src","<spring:url value='/authImage' htmlEscape='true'/>?"+new Date().getTime());
}
</script>




<script type="text/javascript" src="<%=path %>/static/js/common/common.js" ></script>
<script type="text/javascript">
//定义异常信息数组
var arrTips = [
'正在登陆...', //0
'您需要登录后才能继续浏览或操作！', //1
'请输入用户账号！', //2
'请输入密码!', //3
'请输入验证码!', //4
'验证码输入错误!', //5
'账号或密码不正确!请重新输入!', //6
'账号或密码不正确!请重新输入!', //7
'账号已经被锁定，请联系管理员!', //8
'账号已注销，请联系管理员!', //9
'IP异常登录!', //10
'您的帐户已过有效期!', //11
'你的密码超过30天未修改，已经被锁定！', //12
'对不起，你的输入数据格式不合法！', //13
'', //14
'对不起，您的密码输错五次，已经被锁定！', //15
'对不起，您的商户信息未找到！', //16
'对不起，您商户的合同已到期！', //17
'对不起，请确认来源地址是否正确！' //18

];




//登陆
var dologin = function(){
	var usercode = document.getElementById("usercode").value;
	var password = document.getElementById("password").value;
	var rand = document.getElementById("rand").value;
	if(usercode == '' || usercode == null){
		$('#empname').css({'visibility':'inherit'}).fadeIn('slow');
		return;
	}
	if(password == '' || password == null){
		$('#emppwd').css({'visibility':'inherit'}).fadeIn('slow');
		return;
	}
	if(rand == '' || rand == null){
		$('#emprand').css({'visibility':'inherit'}).fadeIn('slow');
		return;
	}
	
	//document.getElementById("loginForm").action="<spring:url value='/system/user_login.do' htmlEscape='true'/>";
	//document.getElementById("loginForm").submit();
	//ajax请求后台，处理
	jQuery.ajax({
		url: "<spring:url value="/system/user_login.do" htmlEscape="true" />",
		method:"post",
		data:{usercode:usercode,password:password,rand:rand},
		success: function(data){
			var result = eval('(' + data + ')');
			//alert(result.msg);
			if(result.data == '0'){
				window.location='<%=path %>/system/index';
			}else{
				alert(arrTips[result.data]);
			}
	     /* var span_new = document.getElementById(spanName);	
		 span_new.style.display = "none";
		 span_new.innerHTML = ""; */
	   }
	});
}

	//隐藏提示
	$('#usercode').focus(function(){
		$('#empname').css({'visibility':'hidden'});
	});
	$('#password').focus(function(){
		$('#emppwd').css({'visibility':'hidden'});
	});
	$('#rand').focus(function(){
		$('#emprand').css({'visibility':'hidden'});
	});

	/*回车事件*/
$("input").keydown(function(e){
	if(e.which == 13){
		dologin();	
	}
});
</script>
</body>

    <!-- END BODY -->
</html>
