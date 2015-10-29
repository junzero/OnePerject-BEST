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
	                        <h5>系统管理-消费记录管理</h5>
	                    </div>
	                </div>
	                <hr />
	                <div class="page-header mr0">
						<form id="aUserSearchForm" name="aUserSearchForm"
							action="<spring:url value='/buyRecordManager.do' htmlEscape='true'/>"
							method="post" target="_self">
		            		<div class="form-group">
								<div class="">
									<i class="icon-hand-right"></i><span>搜索</span> 
									
									<input id="name" class="form-control" type="text" name="cardNum" value="${cardNum }"
										placeholder="会员卡号"> 
								
									
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
													<th class="center">操作</th>
													<th>会员卡号</th>
													<th>会员姓名</th>
													<th>消费总额</th>
													<th>获得积分</th>
													<th>消费时间</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${buyRecords}" var="record"
													varStatus="status">
													<tr>
														<td width="60">
															<div class="btn-group">
																<button type="button" class="btn btn-default dropdown-toggle"
																	data-toggle="dropdown" aria-haspopup="true"
																	aria-expanded="false">
																	操作<span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a data-toggle="modal" href="#auserEdit" title="编辑会员卡"
																		onClick="editrecord('${record.id}');">编辑</a></li>
																	<li><a data-toggle="modal" href="#auserDel" title="删除会员卡"  onClick="delrecord('${record.id}');">删除</a></li>
																	<li><a data-toggle="modal" href="#auserDel" title="消费详情"  onClick="recordDetail('${record.id}','${record.buyTime}');">消费详情</a></li>
																</ul>
															</div>
														</td>
														<td>${record.cardNum}</td>
														<td>${record.memberName}</td>
														<td>${record.totalPrice}</td>
														<td>${record.point}</td>
														<td>${record.buyTime}</td>
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
								<a onclick="addrecord();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
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
    var recordDetail = function(recordId, date){
    	var diag = new zDialog();
    	diag.Height =400;
		diag.Width = 400;
    	diag.Title = "消费详情";
    	diag.URL = "<%=path %>/recordDetail.do?recordId="+recordId;
    	diag.show();
    };
    var addrecord = function(){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
        	diag.Title = "系统管理-消费记录新增";
        	diag.URL = "<%=path %>/toAddBuyRecord.do";
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
    var editrecord = function(id){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
    		diag.Title = "系统管理-消费记录编辑";
        	diag.URL = "<%=path %>/toEditBuyRecord.do?id="+id;
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
    var delrecord= function(id){
    	$('#del_recordId').val(id);
    	zDialog.confirm('警告：您确认要删除该消费记录吗？',function(){
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
					document.getElementById("type").value=auStatus;
				}
				var submitSearchForm = function(){
					document.getElementById("aUserSearchForm").submit();
				}
</script>    

<form id="delForm" name="delForm" method="post" action="delBuyRecord.do" target="thisFrame">
	<input type="hidden" id="del_recordId" name="id">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
