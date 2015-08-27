<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>


<!-- include HEAD -->
<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp" %>
<style type="text/css">
	.form-control{
		background-color: #fff;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    color: #555;
	    font-size: 14px;
	    height: 34px;
	    line-height: 1.42857;
	    padding: 6px 12px;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    vertical-align: middle;
	    display: inline;
	    width:15%;
	}
	.mr0{
		margin: 0px 0px 0px 0px;
	}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="padTop53 " >

        
	<div id="wrap">
        <%@ include file="../main/top.jsp" %>
        <!-- END HEADER SECTION -->



        <%@ include file="../main/left.jsp" %>
        <!--END MENU SECTION -->



        <!--PAGE CONTENT -->
        <div id="content">
            <div class="inner" style="min-height:1200px;">
                <div class="row">
                    <div class="col-lg-12">
	                        <h5>系统管理-会员管理</h5>
	                    </div>
	                </div>
	                <hr />
	                <div class="page-header mr0">
						<form id="aUserSearchForm" name="aUserSearchForm"
							action="<spring:url value='/aumanage.do' htmlEscape='true'/>"
							method="post" target="_self">
		            		<div class="form-group">
								<div class="">
									<i class="icon-hand-right"></i><span>搜索</span> 
									<input class="form-control" type="text" name="usercode" value="${usercode }" placeholder="输入会员名">
									<input id="startDate" class="form-control"
										type="text" name="startDate" value="${startDate }"
										placeholder="开始日期" readonly> 
									<input id="endDate" class="form-control" type="text" name="endDate" value="${endDate }"
										placeholder="结束日期" readonly> 
									<select id="auRoleId" name="auRoleId"
										style="height: 33px; width: 120px; background: none repeat scroll 0 0 #f5f5f5 !important;"
										class="form-control" id="form-field-select-3" data-placeholder="选择组织">
										<option value="0">请选择组织</option>
										<c:forEach items="${groupList}" var="role">
											<option value="${group.id }"
												onclick="setAuRoleId('${group.id }');" 
												<c:if test="${group.id == groupId}">selected</c:if>	
											>${group.groupName}
											</option>
										</c:forEach>
									</select> 
									<select id="status"
										style="height: 33px; width: 80px; background: none repeat scroll 0 0 #f5f5f5 !important;"
										class="form-control" id="form-field-select-3" name="status"
										data-placeholder="">
										<option value="0" selected>状态</option>
										<option value="1" onclick="setAuStatus('1');" <c:if test="${status == 1}">selected</c:if>>有效</option>
										<option value="9" onclick="setAuStatus('9');" <c:if test="${status == 9}">selected</c:if>>失效</option>
									</select>
									<button class="btn btn-default" type="button" onClick="submitSearchForm()">
												<i class="icon-search"></i>
								    </button>
								</div>
						
							</div>
						</form>
				</div>
                
                
                        <div class="table-responsive">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">序号</th>
													<th>用户名</th>
													<th>姓名</th>
													<th>组织</th>
													<th>添加时间</th>
													<th>有效期</th>
													<th>上次访问IP</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${appUserList}" var="appUser"
													varStatus="status">
													<tr>
														<td>${appUser.auid}</td>
														<td>${appUser['userName']}</td>
														<td>${appUser.name}</td>
														<td>${appUser.groupName}</td>
														<td>${appUser.startDate}</td>
														<td>${appUser.endDate}</td>
														<td>${appUser.lastLoginIP}</td>
														<td>
															<a data-toggle="modal" href="#auserEdit" 
																onClick="editAuser('');" class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#auserDel"  onClick="delAuser('${appUser.auid}'');" class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
														</td>
													</tr>
												</c:forEach>

												<c:if test="${page.totalRowNum>0}">
													<c:if test="${page.totalRowNum >= pageSize}">
														<tr class="page_c">
															<td colspan="8">${page.display}</td>
														</tr>
													</c:if>
												</c:if>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								<!-- /row -->
							<div
								style="display: inline-block; background-repeat: no-repeat; border-width: 4px; font-size: 13px; line-height: 1.39; padding: 4px 9px;">
								<a onclick="addAppUser();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
                    </div>
                </div>
                
            </div>
        </div>
       <!--END PAGE CONTENT -->
       
       
       
	</div>
    <!--END MAIN WRAPPER -->
	
    <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  splatform-h5 &nbsp;2015 &nbsp;</p>
    </div>
    <!--END FOOTER -->


    <!-- GLOBAL SCRIPTS -->
    <script src="<%=path%>/static/assets/plugins/jquery-2.0.3.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path%>/static/assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <!-- END GLOBAL SCRIPTS -->
    
    <!-- zDialog -->
	<script src="<%=path %>/static/js/zdialog/zDialog.js"></script>
	<script src="<%=path %>/static/js/zdialog/zDrag.js"></script>
    
    
    <script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>
</body>
    <!-- END BODY -->
    
    <script type="text/javascript">
    

  	//组织新增
    var addAppUser = function(){
    		var diag = new zDialog();
    		diag.Height = 360;
        	diag.Title = "系统管理-会员新增";
        	diag.URL = "<%=path %>/toAddAppUser.do";
        	diag.OKEvent = function(){
        		//参数校验
        		var groupName = diag.innerDoc.getElementById('groupName').value;
        		var groupDesc = diag.innerDoc.getElementById('groupDesc').value;
        		if(groupDesc=='' || groupName == ''){
        			zDialog.alert('请填写组织名称和对应描述!');
        			return;
        		}
        		
        		//处理角色选择
        		var roleArr = new Array();
        		//alert(e.value);
        		//alert(e.checked);
        		//传入后台的字符串
        		var roleStr = diag.innerDoc.getElementById("roleStr").value;
        		
        		roleStr="";
        		roleArr=diag.innerDoc.getElementsByName("gRole");
        		for(var i=0;i<roleArr.length;i++){
        			if(roleArr[i].checked){
        				roleStr+= roleArr[i].value+",";  
        			}
        		}
        		//去掉最后一个逗号
        		if(roleStr.charAt(roleStr.length - 1)==","){
        			roleStr=roleStr.substring(0,roleStr.length-1);
        		}
        		//设置以及选择的角色id
        		if(diag.innerDoc.getElementById("roleStr").value==""){										
        			diag.innerDoc.getElementById("roleStr").value = roleStr;
        		}else{
        			//先清空表单的值
        			diag.innerDoc.getElementById("roleStr").value="";
        		}
        		diag.innerDoc.getElementById("roleStr").value = roleStr;
        		
        		//提交表单
        		diag.innerDoc.getElementById('addForm').submit();
        		diag.submited=true;
        	};//点击确定后调用的方法
        	diag.OnLoad=function(){
        		if(diag.submited){
        			diag.openerWindow.location.reload();
                    try{
        				diag.close();
                    }catch(e){}
        		}
        	};
        	diag.CancelEvent = function(){diag.close();};
        	diag.show();
    }
    
    //在父页面提交iframe中的表单
    //组织编辑
    var editSgroup = function(id){
    		var diag = new zDialog();
    		diag.Height = 400;
    		diag.Title = "系统管理-组织编辑";
        	diag.URL = "<%=path %>/toEditGroup.do?gid="+id;
        	diag.OKEvent = function(){
        		
        		//参数校验
        		var groupName = diag.innerDoc.getElementById('groupName').value;
        		var groupDesc = diag.innerDoc.getElementById('groupDesc').value;
        		if(groupDesc=='' || groupName == ''){
        			zDialog.alert('请填写组织名称和对应描述!');
        			return;
        		}
        		
        		//处理角色选择
        		var roleArr = new Array();
        		//alert(e.value);
        		//alert(e.checked);
        		//传入后台的字符串
        		var roleStr = diag.innerDoc.getElementById("roleStr").value;
        		
        		roleStr="";
        		roleArr=diag.innerDoc.getElementsByName("gRole");
        		for(var i=0;i<roleArr.length;i++){
        			if(roleArr[i].checked){
        				roleStr+= roleArr[i].value+",";  
        			}
        		}
        		//去掉最后一个逗号
        		if(roleStr.charAt(roleStr.length - 1)==","){
        			roleStr=roleStr.substring(0,roleStr.length-1);
        		}
        		//设置以及选择的角色id
        		if(diag.innerDoc.getElementById("roleStr").value==""){										
        			diag.innerDoc.getElementById("roleStr").value = roleStr;
        		}else{
        			//先清空表单的值
        			diag.innerDoc.getElementById("roleStr").value="";
        		}
        		diag.innerDoc.getElementById("roleStr").value = roleStr;
        		//alert(roleStr);
        		diag.innerDoc.getElementById('editForm').submit();
        		diag.submited=true;
        	};//点击确定后调用的方法
        	diag.OnLoad=function(){
        		if(diag.submited){
        			diag.openerWindow.location.reload();
                    try{
        				diag.close();
                    }catch(e){}
        		}
        	};
        	diag.CancelEvent = function(){diag.close();};
        	diag.show();
    }
    
    
    //组织删除
    var delSgroup= function(id,groupName){
    	$('#del-groupId').val(id);
    	zDialog.confirm('警告：您确认要删除组织['+groupName+']吗？',function(){
    		document.getElementById('delForm').submit();diag.close();
    	});
    }
    </script>



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

<form id="delForm" name="delForm" method="post" action="doDelGroup.do" target="thisFrame">
	<input type="hidden" id="del-groupId" name="groupId">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
