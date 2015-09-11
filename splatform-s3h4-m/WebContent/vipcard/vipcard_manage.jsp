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
							action="<spring:url value='/vipcardManager.do' htmlEscape='true'/>"
							method="post" target="_self">
		            		<div class="form-group">
								<div class="">
									<i class="icon-hand-right"></i><span>搜索</span> 
									
									<input id="cardNum" class="form-control" type="text" name="cardNum" value="${cardNum }"
										placeholder="会员卡号"> 
								
									<select id="status"
										style="height: 33px; width: 80px; background: none repeat scroll 0 0 #f5f5f5 !important;"
										class="form-control" id="form-field-select-3" name="status"
										data-placeholder="">
										<option value="" selected>全部</option>
										<option value="0">未开通</option>
										<option value="1" onclick="setAuStatus('1');" <c:if test="${status == 1}">selected</c:if>>已开通</option>
										<option value="2" onclick="setAuStatus('2');" <c:if test="${status == 2}">selected</c:if>>注销</option>
										<option value="3" onclick="setAuStatus('3');" <c:if test="${status == 3}">selected</c:if>>挂失</option>
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
													<th>会员卡号</th>
													<th>绑定会员</th>
													<th>开通日期</th>
													<th>有效期</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${vipcardList}" var="vipcard"
													varStatus="status">
													<tr>
														<td>${vipcard.id}</td>
														<td>${vipcard.cardNum}</td>
														<td>${vipcard.member.name}</td>
														<td>${vipcard.openTime}</td>
														<td>${vipcard.deadline}</td>
														<td>
															<c:if test="${vipcard.status eq '0'}">未开通</c:if>
															<c:if test="${vipcard.status eq '1'}">已开通</c:if>
															<c:if test="${vipcard.status eq '2'}">注销</c:if>
															<c:if test="${vipcard.status eq '3'}">挂失</c:if>														
														</td>
														<td>
															<a data-toggle="modal" href="#auserEdit" title="编辑会员卡"
																onClick="editVipcard('${vipcard.cardNum}');" class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#auserDel" title="删除会员卡"  onClick="delVipcard('${vipcard.cardNum}');" class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
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
								<a onclick="addVipcard();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
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
    <script type="text/javascript"	src="<%=path %>/static/js/Validform_v5.3.2.js"></script>
    <script type="text/javascript">

    var addVipcard = function(){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
        	diag.Title = "系统管理-会员卡新增";
        	diag.URL = "<%=path %>/toAddVipcard.do";
        	diag.OKEvent = function(){
        		//参数校验
        		if(diag.innerWin.check()){
        			diag.innerDoc.getElementById('addForm').submit();
        		}
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
    var editVipcard = function(cardNum){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
    		diag.Title = "系统管理-会员卡编辑";
        	diag.URL = "<%=path %>/toEditVipcard.do?cardNum="+cardNum;
        	diag.OKEvent = function(){
        		
        		//参数校验
        		if(diag.innerWin.check()){
        			diag.innerDoc.getElementById('editForm').submit();
        		}
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
    var delVipcard= function(cardNum){
    	$('#del_cardNum').val(cardNum);
    	zDialog.confirm('警告：您确认要删除会员卡['+cardNum+']吗？',function(){
    		document.getElementById('delForm').submit();diag.close();
    	});
    }
    </script>



<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				
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

<form id="delForm" name="delForm" method="post" action="delVipcard.do" target="thisFrame">
	<input type="hidden" id="del_cardNum" name="cardNum">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
