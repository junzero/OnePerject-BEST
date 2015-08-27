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
    margin:20px 0 0 20px;
}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
					<form class="_formm15" method="post" id="addForm" name="addForm"
						action="<spring:url value='/auserAdd.do' htmlEscape='true'/>" target="_self">
						<ul class="box_l_h_c">
							<li class="box_l_h_c_li">
								<select id="groupId" name="groupId"
									style="width: 159px; background: none repeat scroll 0 0 #f5f5f5 !important;">
										<option value="0" selected>请选择组织</option>
										<c:forEach items="${groupList}" var="group">
											<option value="${group.id }">${group.groupName }</option>												onclick="setAuRoleId('${role.id }');">${role.roleName}</option>
										</c:forEach>
								</select>
							</li>
							<li class="box_l_h_c_li">
								<input id="createDate" class="span3" type="text"
									name="startDate" value="" placeholder="开始日期" readonly>
							</li>
						<li class="box_l_h_c_li"><input name="username" value="" type="text"
									placeholder="输入用户名" class="ui-autocomplete-input" id=""
									autocomplete="off" /></li>
							<li class="box_l_h_c_li">
								<input id="validDate" class="span3" type="text"
									name="endDate" value="" placeholder="结束日期" readonly>
							</li>
							
							<li class="box_l_h_c_li">
								<input name="password" id="password" value="" type="password"
									placeholder="输入密码" class="ui-autocomplete-input" 
									autocomplete="off" />
							</li>
							
							<li class="box_l_h_c_li">
								<input name="terminalId" id="terminalId" type="text"
									placeholder="输入手机号" class="ui-autocomplete-input" 
									autocomplete="off" />
							</li>
							
						<li class="box_l_h_c_li">
								<input name="repassword" id="repassword" value="" type="password"
									placeholder="确认密码" class="ui-autocomplete-input" 
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="email" id="email" type="text"
									placeholder="输入邮箱" class="ui-autocomplete-input" 
								autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="name" value="" type="text"
									placeholder="输入姓名" class="ui-autocomplete-input" id="name"
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="limitYear" value="" type="text" id="limitYear"
									placeholder="开通年限(限数字,0-100)" class="ui-autocomplete-input"
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<input name="remark" value="" type="text"
									placeholder="备注" class="ui-autocomplete-input" id=""
									autocomplete="off" />
							</li>
							<li class="box_l_h_c_li">
								<select id="status"
									style="width: 159px; background: none repeat scroll 0 0 #f5f5f5 !important;"
									name="status">
										<option value="0" selected>状态</option>
									<option value="1" onclick="setAuStatus('1');" <c:if test="${status == 1}">selected</c:if>>有效</option>
									<option value="9" onclick="setAuStatus('9');" <c:if test="${status == 9}">selected</c:if>>失效</option>
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
				var setAuRoleId = function(auRoleId){
					document.getElementById("auRoleId").value=auRoleId;
				}
				var setAuStatus = function(auStatus){
					document.getElementById("status").value=auStatus;
				}
				var submitSearchForm = function(){
					document.getElementById("aUserSearchForm").submit();
				}
</script>    
</html>