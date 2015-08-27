<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<link href="<%=path %>/static/js/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<style>
	.role—area{
		border:1px solid #9FA0A3;
		line-height: 40px;
		background-color: #ffffff;
	}
	div.left {
	    float: left;
	}
	div.zTreeDemoBackground {
	    text-align: left;	    
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
<%@ include file="../main/header.jsp"%>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
		<form class="_formm15" action="<%=path%>/doEditGroup.do" method="post" id="editForm" name="editForm">
								<input type="hidden" id="groupId" name="groupId"
											value="${group.id }" />
								<input type="hidden" id="roleStr" name="roleStr"
											value="" >
								<table width="98%" border="0" align="center" cellpadding="4"
									cellspacing="4" bordercolor="#666666">
									<tr>
										<td width="100" align="left">组织名称：</td>
										<td><input type="text" id="groupName" name="groupName" class="form-control wid40"
											value="${group.groupName }" /></td>
									</tr>
									<tr>
										<td width="100" align="left">描述：</td>
										<td><input type="text" id="groupDesc" name="groupDesc" class="form-control wid40"
											value="${group.groupDesc }" /></td>
									</tr>
									<tr>
										<td width="100" align="left">创建时间：</td>
										<td><input type="text" id="createTime" name="createTime" class="form-control wid40"
											value="${group.createTime }" readonly="readonly" /></td>
									</tr>
									<tr>
							          <td width="100" align="left">角色选择:</td>
							          <td width="400" class="role—area">
									   	<ul class="wrap-ul">
									   		<div class="content_wrap">
											<div class="zTreeDemoBackground left">
										   		<c:forEach items="${roleList}" var="role"
														varStatus="status">
										   			<c:choose>
										   				<c:when test="${role.checked==true }">
											   				<li class="wrap-li">
											   					<input name="gRole" type="checkbox" value="${role.id}" checked="checked">${role.roleName}</li>
											   			</c:when>
											   			<c:otherwise>
											   				<li class="wrap-li">
											   					<input name="gRole" type="checkbox" value="${role.id}"> ${role.roleName}</li>
											   			</c:otherwise>	
										   			</c:choose>					   			
										   		</c:forEach>
										   		</div>
										   	</div>
									   	</ul>
									  </td>
							        </tr>
								</table>
							</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>

<!-- zTree -->
<script type="text/javascript" src="<%=path %>/static/js/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>

<SCRIPT type="text/javascript">
		
		/*
		<div class="content_wrap">	
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
		</div>
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"}
		];
		
		var code;
		
		function setCheck() {
			*var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			 py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			 setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck); 
		}); */
		
	</SCRIPT>
</html>