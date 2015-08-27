﻿<%@page import="com.sh.manage.constants.SessionConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>控制台-Bootstrap后台管理系统</title>
<meta name="keywords" content="Bootstrap模版" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 引入js -->
<jsp:include page="../static/js/js_inc.jsp"></jsp:include>

<!-- basic styles -->
<link href="<%=path %>/static/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=path %>/static/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=path %>/static/css/bootstrap-select.css">

<style type="text/css">
.page_c {
	text-align: right;
}

.span2 {
	width: 120px;
}

.span3 {
	width: 191px;
}

.chosen-container-single .chosen-single {
	border-radius: 0;
	color: #939192;
}

.box_l_h_c {
	margin:0 0;
	width: 398px;
	background-color: #EFEFEF;
	border-radius: 3px;
	overflow: hidden;
}

.mzinfo_box_l ul .box_l_h_c_li {
	margin-left: 6px;
	margin-right: 10px;
	width: 180px;
	float: left;
	line-height: 22px;
	list-style-type: none;
}

</style>
<!--[if IE 7]>
		  <link rel="stylesheet" href="<%=path %>/static/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet"
	href="<%=path %>/static/assets/css/font-awesome.min.css" />

<!-- ace styles -->

<link rel="stylesheet" href="<%=path %>/static/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="<%=path %>/static/assets/css/ace-rtl.min.css" />
<link rel="stylesheet"
	href="<%=path %>/static/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="<%=path %>/static/assets/css/datepicker.css" />
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%=path %>/static/assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->
<style type="text/css">
.span2 {
	width: 120px;
}
</style>
<!-- ace settings handler -->

<script src="<%=path %>/static/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="<%=path %>/static/assets/js/html5shiv.js"></script>
		<script src="<%=path %>/static/assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<!-- BEGIN BODY -->
<body class="padTop53 " >

        
	<div id="wrap">
        <%@ include file="../main/top.jsp" %>
        <!-- END HEADER SECTION -->



        <%@ include file="../main/left.jsp" %>
        <!--END MENU SECTION -->

				
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->


	</div>
	<!-- /.main-container-inner -->

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
	</div>
	<!-- /.main-container -->

<!-- 弹出添加页 -->
<div id="modal-table" class="modal fade" tabindex="-1">
		<div class="modal-dialog" style="width: 420px;">
			<div class="modal-content" style="top: 150px;">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" style="font-size:25px;">
							<span class="white">&times;</span>
						</button>
						<i class="icon-save"></i>&nbsp;用户添加
					</div>
				</div>

				<div class="mzinfo_box_l" style="line-height: 50px;">
					<form id="suserAddForm" name="suserAddForm"
						action="<spring:url value='/suserAdd.do' htmlEscape='true'/>"
						method="post" target="targetFrame">
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li">
								<select id="suRoleId" name="suRoleId"
									style="height:28px;width: 192px; background: none repeat scroll 0 0 #f5f5f5 !important;">
										<option value="0" selected>请选择角色</option>
										<c:forEach items="${roleList}" var="role">
											<option value="${role.id }"
												onclick="setSuRoleId('${role.id }','${role.roleName}');"
											>${role.roleName}</option>
										</c:forEach>
								</select>
							</li>
							<li class="box_l_h_c_li">
								<input id="createDate" class="span3" type="text"
									name="startDate" value="" placeholder="开始日期" readonly>
							</li>
							<li class="box_l_h_c_li"><input name="usercode" value="" type="text"
									placeholder="输入用户名" class="ui-autocomplete-input span3" id=""
									autocomplete="off" /></li>
							<li class="box_l_h_c_li">
								<input id="validDate" class="span3" type="text"
									name="endDate" value="" placeholder="结束日期" readonly>
							</li>
							
							<li class="box_l_h_c_li">
								<input name="password" id="password" value="" type="password"
									placeholder="输入密码" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<input name="terminalId" id="terminalId" type="text"
									placeholder="输入手机号" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<input name="repassword" id="repassword" value="" type="password"
									placeholder="确认密码" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="email" id="email" type="text"
									placeholder="输入邮箱" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="name" value="" type="text"
									placeholder="输入姓名" class="ui-autocomplete-input span3" id="name"
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<select id="status"
									style="height:28px;width: 192px; background: none repeat scroll 0 0 #f5f5f5 !important;"
									name="status">
										<option value="0">状态</option>
										<option value="1" selected onclick="setSuStatus('1');">有效</option>
										<option value="9" onclick="setSuStatus('9');">失效</option>
								</select>
							</li>
							<div style="text-align: center;clear:both;">
									<button class="btn btn-sm btn-danger" data-dismiss="modal">
										<i class="icon-remove"></i> 取消
									</button>
									<button type="button" class="btn btn-sm btn-success"
										onClick="submitSuserAddForm();">
										确认 <i class="icon-arrow-right icon-on-right"></i>
									</button>
							</div>
						</ul>
						
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
<!-- 弹出修改页 -->
<div id="suserEdit" class="modal fade" tabindex="-1">
		<div class="modal-dialog" style="width: 420px;">
			<div class="modal-content" style="top: 150px;">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true" style="font-size:25px;">
							<span class="white">&times;</span>
						</button>
						<i class="icon-save"></i>&nbsp;用户修改
					</div>
				</div>

				<div class="mzinfo_box_l" style="line-height: 50px;">
					<form id="suserEditForm" name="suserEditForm"
						action="<spring:url value='/suserEdit.do' htmlEscape='true'/>"
						method="post" target="targetFrame">
						<input type="hidden" id="uidEdit" name="uid"/>
						<input type="hidden" id="suRoleIdEdit" name="suRoleId"/>
						
						
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li">
								<select id="suRoleIdEdit"
									style="height:28px;width: 192px; background: none repeat scroll 0 0 #f5f5f5 !important;">
										<option value="0" selected>请选择角色</option>
										<c:forEach items="${roleList}" var="role">
											<option value="${role.id }"
												onclick="setSuRoleIdEdit('${role.id }');"	
											>${role.roleName}</option>
										</c:forEach>
								</select>
							</li>
							<input type="hidden" id="roleName" name="roleName" value=""/>
							<li class="box_l_h_c_li">
								<input id="createDateEdit" class="span3" type="text"
									name="startDate" value="" placeholder="开始日期" readonly>
							</li>
							<li class="box_l_h_c_li"><input name="usercode" value="" type="text"
									placeholder="输入用户名" class="ui-autocomplete-input span3" id="usercodeEdit"
									autocomplete="off" /></li>
							<li class="box_l_h_c_li">
								<input id="validDateEdit" class="span3" type="text"
									name="endDate" value="" placeholder="结束日期" readonly>
							</li>
							
							<li class="box_l_h_c_li">
								<input name="terminalId" id="terminalIdEdit" type="text"
									placeholder="输入手机号" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="email" id="emailEdit" type="text"
									placeholder="输入邮箱" class="ui-autocomplete-input span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="name" value="" type="text"
									placeholder="输入姓名" class="ui-autocomplete-input span3" id="nameEdit"
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<select id="statusEdit"
									style="height:28px;width: 192px; background: none repeat scroll 0 0 #f5f5f5 !important;"
									name="status">
										<option value="0">状态</option>
										<option value="1" selected onclick="setSuStatus('1');">有效</option>
										<option value="9" onclick="setSuStatus('9');">失效</option>
								</select>
							</li>
							<div style="text-align: center;clear:both;">
									<button class="btn btn-sm btn-danger" aria-hidden="true" data-dismiss="modal">
										<i class="icon-remove"></i> 取消
									</button>
									<button type="button" class="btn btn-sm btn-success"
										onClick="submitSuserEditForm();">
										确认 <i class="icon-arrow-right icon-on-right"></i>
									</button>
							</div>
						</ul>
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>	
<!-- 弹出删除页 -->
<div id="suserDel" class="modal fade" tabindex="-1">
		<div class="modal-dialog" style="width: 420px;">
			<div class="modal-content" style="top: 150px;">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						<i class="icon-save"></i>&nbsp;用户删除
					</div>
				</div>

				<div class="mzinfo_box_l" style="line-height: 50px;">
					<form id="suserDelForm" name="suserDelForm"
						action="<spring:url value='/suserDel.do' htmlEscape='true'/>"
						method="post" target="targetFrame">
						<input type="hidden" id="uidDel" name="uid"/>
						<input type="hidden" id="suRoleIdDel" name="suRoleId"/>
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li" style="font-size: 14px;line-height: 55px;width:220px;">
								确认要删除该用户[<span id="usercodeDel"></span>]吗?
							</li>

							<div style="text-align: center;clear:both;">
									<button class="btn btn-sm btn-danger"
										aria-hidden="true" data-dismiss="modal" type="button">
										<i class="icon-remove"></i> 取消
									</button>
									<button type="button" class="btn btn-sm btn-success"
										onClick="submitSuserDelForm();">
										确认 <i class="icon-arrow-right icon-on-right"></i>
									</button>
							</div>
						</ul>
						
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
</div>	

<!-- 隐藏iframe -->
<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>

<!-- basic scripts -->
<script type="text/javascript">
//用户信息新增
var submitSuserAddForm = function(){
	var usercode = $("#usercode").val();
	var name = $("#name").val();
	if(usercode == '' || name == ''){
		alert('请输入用户名和姓名');
		$("input[type='text'][name='usercode']").focus();
		return;
	}
	//密码确认校验
	var pwd = $("#password").val();
	var rePwd = $("#repassword").val();
	if(pwd != rePwd ){
		alert('两次输入密码不一致,请确认');
		$("input[type='text'][name='password']").focus();
		return;
	}
	if(pwd == '' || rePwd ==''){
		alert('请输入密码和确认密码');
		$("input[type='text'][name='password']").focus();
		return;
	}

	//邮箱校验
	//邮箱正则
	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var v_email = $("#email").val();
	if(v_email !=''){
		if(!emailReg.test(v_email)){
			alert('请输入有效的E_mail！');
	        $("input[type='text'][name='email']").focus();
	        return;
		}
	}else{
		alert('请输入E_mail！');
        $("input[type='text'][name='email']").focus();
        return;
	}
	
	//手机号校验
	var v_ter = $("#terminalId").val();
	if(v_ter==""||v_ter.length<11){
		alert("请输入正确的手机号码!");
		$("input[type='text'][name='terminalId']").focus();
		return;
	}
	
	document.getElementById("suserAddForm").submit();
}


//用户信息修改
var editSuser = function(a,b,c,d,e,f,g,h){
	
	//document.getElementById("suRoleIdEdit").value = ;
	document.getElementById("createDateEdit").value = a;
	document.getElementById("usercodeEdit").value = b;
	document.getElementById("validDateEdit").value = c;
	document.getElementById("terminalIdEdit").value = d;
	document.getElementById("emailEdit").value = e;
	document.getElementById("nameEdit").value = f;
	document.getElementById("statusEdit").value = g;
	
	document.getElementById("uidEdit").value = h;
	
	document.getElementById("suRoleIdEdit").value = g;
}

//提交用户信息修改
var submitSuserEditForm = function(){
	var roleId = $("#suRoleIdEdit").val();
	if(roleId = null || roleId == 0){
		alert('请选择角色');
		return;
	}
	
	var name = $("#nameEdit").val();
	if(name == ''){
		alert('请输入姓名');
		$("#nameEdit").focus();
		return;
	}
	//邮箱校验
	//邮箱正则
	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	var v_email = $("#emailEdit").val();
	if(v_email !=''){
		if(!emailReg.test(v_email)){
			alert('请输入有效的E_mail！');
	        $("#emailEdit").focus();
	        return;
		}
	}else{
		alert('请输入E_mail！');
        $("#emailEdit").focus();
        return;
	}
	
	//手机号校验
	var v_ter = $("#terminalIdEdit").val();
	if(v_ter==""||v_ter.length<11){
		alert("请输入正确的手机号码!");
		$("#terminalIdEdit").focus();
		return;
	}
	
	document.getElementById("suserEditForm").submit();
}


//删除用户信息设置
var delSuser = function(uid,usercode,uRoleId){
	document.getElementById("suRoleIdDel").value = uRoleId;
	document.getElementById("uidDel").value = uid;
	document.getElementById("usercodeDel").innerHTML = usercode;
}
//提交用户删除
var submitSuserDelForm = function(){
	document.getElementById("suserDelForm").submit();
}


</script>
	
	
	<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=path %>/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%=path %>/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
	<script src="<%=path %>/static/assets/js/bootstrap.min.js"></script>
	<script src="<%=path %>/static/assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="<%=path %>/static/assets/js/excanvas.min.js"></script>
		<![endif]-->

	<script
		src="<%=path %>/static/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="<%=path %>/static/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="<%=path %>/static/assets/js/jquery.slimscroll.min.js"></script>
	<script src="<%=path %>/static/assets/js/jquery.easy-pie-chart.min.js"></script>
	<script src="<%=path %>/static/assets/js/jquery.sparkline.min.js"></script>



	<script src="<%=path %>/static/assets/js/date-time/bootstrap-datepicker.js"></script>

	<script type="text/javascript"	src="<%=path %>/static/js/bootstrap-select.js"></script>
	<!-- inline scripts related to this page -->

<script type="text/javascript">
		$('#startDate').datepicker({format:"yyyy-mm-dd"});
		$('#endDate').datepicker({format:"yyyy-mm-dd"});
		$('#createDate').datepicker({format:"yyyy-mm-dd"});
		$('#validDate').datepicker({format:"yyyy-mm-dd"});
		$('#createDateEdit').datepicker({format:"yyyy-mm-dd"});
		$('#validDateEdit').datepicker({format:"yyyy-mm-dd"});
		
		//提交搜索
		var setSuRoleId = function(suRoleId,roleName){
			document.getElementById("suRoleId").value=suRoleId;
			document.getElementById("roleName").value=roleName;
		}
		var setSuStatus = function(suStatus){
			document.getElementById("status").value=suStatus;
		}
		var submitSearchForm = function(){
			document.getElementById("suserSearchForm").submit();
		}
		
		
		//修改设置角色id
		var setSuRoleIdEdit = function(suRoleId){
			document.getElementById("suRoleIdEdit").value=suRoleId;
		}
</script>
<script type="text/javascript">
     $(window).on('load', function () {

         $('.selectpicker').selectpicker({
             'selectedText': 'cat'
         });

     })
</script>
</body>
</html>

