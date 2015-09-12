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
	                        <h5>系统管理-商品管理</h5>
	                    </div>
	                </div>
	                <hr />
	                <div class="page-header mr0">
						<form id="aUserSearchForm" name="aUserSearchForm"
							action="<spring:url value='/goodsManager.do' htmlEscape='true'/>"
							method="post" target="_self">
		            		<div class="form-group">
								<div class="">
									<i class="icon-hand-right"></i><span>搜索</span> 
									
									<input id="name" class="form-control" type="text" name="name" value="${name }"
										placeholder="商品名称"> 
								
									<select id="type"
										style="height: 33px; width: 80px; background: none repeat scroll 0 0 #f5f5f5 !important;"
										class="form-control" id="form-field-select-3" name="type"
										data-placeholder="">
										<option value="" selected>全部</option>
										<option value="1" onclick="setAuStatus('1');" <c:if test="${status == 1}">selected</c:if>>产品</option>
										<option value="2" onclick="setAuStatus('2');" <c:if test="${status == 2}">selected</c:if>>服务</option>
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
													<th class="center">操作</th>
													<th>商品名称</th>
													<th>商品类型</th>
													<th>商品价格</th>
													<th>商品描述</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${goodsList}" var="goods"
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
																		onClick="editGoods('${goods.id}');">编辑</a></li>
																	<li><a data-toggle="modal" href="#auserDel" title="删除会员卡"  onClick="delGoods('${goods.id}');">删除</a></li>
																	
																</ul>
															</div>
														</td>
														<td>${goods.name}</td>
														<td><c:if test="${goods.type eq '1'}">产品</c:if>
															<c:if test="${goods.type eq '2'}">服务</c:if></td>
														<td>${goods.price}</td>
														<td>${goods.description}</td>
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
								<a onclick="addGoods();" href="javascript:;" class="btn btn-success btn-sm">添加</a>
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

    var addGoods = function(){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
        	diag.Title = "系统管理-商品新增";
        	diag.URL = "<%=path %>/toAddGoods.do";
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
    var editGoods = function(id){
    		var diag = new zDialog();
    		diag.Height = 360;
    		diag.Width = 600;
    		diag.Title = "系统管理-商品编辑";
        	diag.URL = "<%=path %>/toEditGoods.do?id="+id;
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
    var delGoods= function(id){
    	$('#del_goodsId').val(id);
    	zDialog.confirm('警告：您确认要删除该商品吗？',function(){
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

<form id="delForm" name="delForm" method="post" action="delGoods.do" target="thisFrame">
	<input type="hidden" id="del_goodsId" name="id">
</form>
<iframe style="display: none" name="thisFrame"></iframe>
</html>
