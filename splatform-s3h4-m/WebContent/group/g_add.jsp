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
	}
	div.zTreeDemoBackground {
	    border: 1px solid #9fa0a3;
	    height: 210px;
	    text-align: left;
	    width: 400px;
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
	.wid40{
		width:40%;
	}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
		<form class="_formm15" action="<%=path%>/doAddGroup.do" method="post" id="addForm" name="addForm">
								<input type="hidden" id="groupId" name="groupId"
											value="" />
								<input type="hidden" id="roleStr" name="roleStr"
											value="" >
								<table width="100%" border="0" align="center" cellpadding="4"
									cellspacing="4" bordercolor="#666666">
									<tr>
										<td width="100" align="left">组织名称：</td>
										<td><input type="text" id="groupName" name="groupName" class="form-control wid40"
											value="" /></td>
									</tr>
									<tr>
										<td width="100" align="left">描述：</td>
										<td><input type="text" id="groupDesc" name="groupDesc" class="form-control wid40"
											value="" /></td>
									</tr>
									<tr>
							          <td width="100" align="left">角色选择:</td>
							          <td width="400" class="">
							          	<div class="content_wrap">
											<div class="zTreeDemoBackground left">
											   	<ul class="wrap-ul">
											   		<c:forEach items="${roleList}" var="role"
															varStatus="status">
											   			<c:choose>
											   				<c:when test="${role.checked==true }">
												   				<li class="wrap-li">
												   					<input name="gRole" type="checkbox" checked="checked" value="${role.id}">${role.roleName}</li>
												   			</c:when>
												   			<c:otherwise>
												   				<li class="wrap-li">
												   					<input name="gRole" type="checkbox" value="${role.id}"> ${role.roleName}</li>
												   			</c:otherwise>	
											   			</c:choose>					   			
											   		</c:forEach>
											   	</ul>
										   	</div>
									   	</div>
									  </td>
							        </tr>
								</table>
		</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->
<script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>
</html>