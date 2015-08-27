<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../WEB-INF/include.jsp" %>
<html>
<head>
<title>权限管理--角色编辑</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=path %>/static/css/bootstrap.css" type="text/css" rel="stylesheet"/>
	
		
	<link href="<%=path %>/static/js/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
	
	<style type="text/css">
		.content{
			margin-top:2%;
		}
		.btn-sea{
			font-size:14px;
			padding:0px 1px;
			width:80px;
			height:25px;
			vertical-align: middle;
			
		}
		.input-sea{
			font-size:12px;
			padding:0px 1px;
			width:100px;
			height:25px;
			vertical-align: middle;
		}
		
		.content ul{
			
		}
		.content li{
			float: left;
			margin: 0 6px 0 0 ;
		}
		.tab-content{
			height: 26px;
		}
		.f-1{
			float: left;
			border-bottom: 2px solid #005CB1;
		}
		.f-1 .active{
			background-color: #005CB1;
			color:#fff;
			border-color:#005CB1;
		}
		.mid-content{
			margin:0 0 10px 0;
			border-bottom: 1px solid #c5c5c5;
			width:450px;
		}
		.bott-content{
			padding:0;
		}
		.page_c {
			text-align: right;
			font-size: 12px;
		}
		.bui-grid-cell-text{
			font-size: 12px;
		}
		#pageNo{
			width:40px;
		}
		.nodata{
			margin:10px 0 0 30px;
		}
		input.calendar {
			height: 22px;
    		width: 120px;
		}
		
		.form-horizontal .mid-controls{
			display: inline;
		}
		.mt10{
			margin-top:10px;
		}
		.mf10{
			margin-left:10px;
		}
		.mb5{
			margin-bottom:5px;
		}
		div.left {
		    float: left;
		}
		div.zTreeDemoBackground {
		    text-align: left;
		    width: 200px;
		    height: 300px;
		    border:1px solid #9FA0A3;
		}
		.wid40{
			width:40%;
		}
	</style>
	
	<!-- 引入公共js组件 -->
<%-- 	<jsp:include page="../../static/js/js_inc.jsp"></jsp:include>
 --%>
</head>
<body>
<div class="content" >
	<div id="forlogin">
      <form class="_formm15" action="<%=path%>/doEditRole.do" method="post" id="editForm" name="editForm" target="targetFrame">
		<input type="hidden" id="roleStr" name="roleMenuStr"
					value="" >
		<input type="hidden" id="roleId" name="roleId"
					value="${sysRole.id }" >			
	      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
	        <tr>
	          <td width="100" align="left">角色名称：</td>
	          <td ><input type="text" id="roleName" name="roleName" class="form-control wid40" value="${sysRole.roleName }"/></td>
	        </tr>
	        <tr>
	          <td width="100" align="left">描述：</td>
	          <td ><input type="text" id="remark" name="remark" class="form-control wid40" value="${sysRole.remark }"/></td>
	        </tr>
	        <tr>
	          <td width="100" align="left">权限选择:</td>
	          <td width="400" class="">
			        <div class="content_wrap">
							<div class="zTreeDemoBackground left">
								<ul id="treeDemo" class="ztree"></ul>
							</div>		
					</div>
			  </td>
	        </tr>
	        <!-- <tr>
	          <td colspan="2" align="center">
		          <input type="button" onClick="fun3()" value="确认" /> 
		          <input onClick="fun2()" type="button" value="取消" />
	          </td>
	        </tr> -->
	      </table>
	    </form>
     
</div>
</div>	
<!-- END content -->
  
  
  
  <script type="text/javascript" src="<%=path %>/static/js/jquery-1.7.2.min.js"></script>

  <!-- zdialog -->
  <script type="text/javascript" src="<%=path %>/static/js/zdialog/zDrag.js"></script>
  <script type="text/javascript" src="<%=path %>/static/js/zdialog/zDialog.js"></script>

  <!-- zTree -->
  <script type="text/javascript" src="<%=path %>/static/js/ztree/jquery.ztree.core-3.5.min.js"></script>
  <script type="text/javascript" src="<%=path %>/static/js/ztree/jquery.ztree.excheck-3.5.min.js"></script>
  <script>
	$('#btnSearch').click(function(){
		
		$('#searchForm').submit();
	});
  </script>
  <script>
    var jumpPage = function(url,muCode){
    	
    	$('#hd-form').attr('action','<%=path %>/'+url);
    	$('#muCode').val(muCode);
    	$('#hd-form').submit();
    }
    

  </script>
  <SCRIPT type="text/javascript">
		
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};

		var zNodes = ${menuStrs};
		
		
		function onCheck(e, treeId, treeNode) {
			//1.选择的节点
			var zTree = $.fn.zTree.getZTreeObj(treeId),
			nodes = zTree.getCheckedNodes(true);
			
			//2.处理角色权限选择
			roleStr="";
			if (nodes.length > 0) {
				for(var i=0;i<nodes.length;i++){
					var tId = nodes[i].id;
				 	if(tId > 0){
					 	roleStr += tId+",";
					}
				}
			}

			//3.去掉最后一个逗号
    		if(roleStr.charAt(roleStr.length - 1)==","){
    			roleStr=roleStr.substring(0,roleStr.length-1);
    		}
			
    		//4.设置以及选择的角色id
    		if(document.getElementById("roleStr").value==""){										
    			document.getElementById("roleStr").value = roleStr;
    		}else{
    			//先清空表单的值
    			document.getElementById("roleStr").value="";
    		}
    		document.getElementById("roleStr").value = roleStr;
    		//5.传入后台的字符串
    		/*var roleStr = document.getElementById("roleStr").value;*/
		}
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
	</SCRIPT>

  <iframe name="targetFrame" style="display: none"></iframe>
</body>
</html>