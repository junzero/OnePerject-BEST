<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp"%>
<style type="text/css">
	.role—area{
		border:1px solid #9FA0A3;
		line-height: 40px;
		background-color: #ffffff;
	}
	div.left {
	    float: left;
	    width: 250px;
	}
	div.zTreeDemoBackground {
	    text-align: left;
	    width: 250px;
	}
	#wrap ul{
		text-align: left;
	}
	.wrap-ul li{
		display:block;
	    float:left;
	    width:110px;
	}
	#wrap ul .wrap-li{
		transition-property: transform, opacity;
		transition-duration: 0.4s;
	}
	.box_l_h_c li{

    display:block;
    float:left;
    margin:20px 0 0 10px;
}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
					<form class="" method="post" id="addForm" name="addForm"
						action="<spring:url value='/doAddsuser.do' htmlEscape='true'/>" target="_self">
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li">
								<select id="suGroupId" name="suGroupId" class="form-control"
									style="height:33px;width: 196px; background: none repeat scroll 0 0 #f5f5f5 !important;">
										<option value="0" selected>请选择组织</option>
										<c:forEach items="${groupList}" var="group">
											<option value="${group.id }"
												onclick="setSuGroupId('${group.id }','${group.groupName}');"
											>${group.groupName}</option>
										</c:forEach>
								</select>
							</li>
							<li class="box_l_h_c_li">
								<input id="createDate" class="form-control span3" type="text"
									name="startDate" value="" placeholder="开始日期" readonly>
							</li>
							<li class="box_l_h_c_li"><input name="usercode" value="" type="text"
									placeholder="输入用户名" class="form-control span3" id="usercode"
									autocomplete="off" /></li>
							<li class="box_l_h_c_li">
								<input id="validDate" class="form-control span3" type="text"
									name="endDate" value="" placeholder="结束日期" readonly>
							</li>
							
							<li class="box_l_h_c_li">
								<input name="password" id="password" value="" type="password"
									placeholder="输入密码" class="form-control span3" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<input name="terminalId" id="terminalId" type="text"
									placeholder="输入手机号" class="form-control span3" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<input name="repassword" id="repassword" value="" type="password"
									placeholder="确认密码" class="form-control span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="email" id="email" type="text"
									placeholder="输入邮箱" class="form-control span3" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="name" value="" type="text"
									placeholder="输入姓名" class="form-control span3" id="name"
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<select id="status" class="form-control"
									style="height:33px;width: 196px; background: none repeat scroll 0 0 #f5f5f5 !important;"
									name="status">
										<option value="0">状态</option>
										<option value="1" selected onclick="setSuStatus('1');">有效</option>
										<option value="9" onclick="setSuStatus('9');">失效</option>
								</select>
							</li>
							
						</ul>
						
					</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->
<script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>

<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>

<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#startDate').datepicker({format:"yyyy-mm-dd"});
				$('#endDate').datepicker({format:"yyyy-mm-dd"});
				$('#createDate').datepicker({format:"yyyy-mm-dd"});
				$('#validDate').datepicker({format:"yyyy-mm-dd"});
				$('#createDateEdit').datepicker({format:"yyyy-mm-dd"});
				$('#validDateEdit').datepicker({format:"yyyy-mm-dd"});
				//提交搜索
				var setSuGroupId = function(suGroupId){
					document.getElementById("suGroupId").value=suGroupId;
				}
				var setSuStatus = function(suStatus){
					document.getElementById("status").value=suStatus;
				}
				
</script>    
</html>