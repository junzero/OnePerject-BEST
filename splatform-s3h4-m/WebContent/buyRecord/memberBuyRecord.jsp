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
<script src="<%=path %>/static/js/zdialog/zDialog.js"></script>
	<script src="<%=path %>/static/js/zdialog/zDrag.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
	var recordDetail = function(recordId, date){
    	var diag = new zDialog();
    	diag.Height =400;
		diag.Width = 400;
    	diag.Title = date+"的消费详情";
    	diag.URL = "<%=path %>/recordDetail.do?recordId="+recordId;
    	diag.show();
    };
</script>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
         
                        <div class="" style="margin-bottom:0px;">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>会员卡号</th>
													<th>会员姓名</th>
													<th>消费总额</th>
													<th>获得积分</th>
													<th>消费时间</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${buyRecords}" var="record"
													varStatus="status">
													<tr>
														
														<td>${record.cardNum}</td>
														<td>${record.memberName}</td>
														<td>${record.totalPrice}</td>
														<td>${record.point}</td>
														<td>${record.buyTime}</td>
														<td><button type="button" class="btn btn-success btn-xs" onClick="recordDetail('${record.id}','${record.buyTime}')">详情</button></td>
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
							
</body>
</html>
