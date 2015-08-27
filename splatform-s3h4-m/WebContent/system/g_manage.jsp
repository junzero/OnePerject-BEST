<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %> 
<%@ include file="/WEB-INF/include.jsp"%>
<!DOCTYPE html>
<html>
<!-- BEGIN HEAD -->
<head>
<style type="text/css">
	.page_c{
		text-align: right;
	}
</style>
</head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
<body class="padTop53 " >
        <!--PAGE CONTENT -->
        <div id="content">
            <div class="inner" style="min-height: 700px;">
                
                  <hr />
                 <!--BLOCK SECTION -->
                 <div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">序号</th>
													<th>组织名称</th>
													<th>描述</th>
													<th>添加时间</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${groupList}" var="sysGroup"
													varStatus="status">
													<tr>
														<td>${sysGroup.id}</td>
														<td>${sysGroup.groupName}</td>
														<td>${sysGroup.groupDesc}</td>
														<td>
															<fmt:parseDate value="${sysGroup.createTime}" pattern="yyyyMMddHHmmss" var="date"/>
															 <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td>														
														<c:if test="${sysUser.status == 1}">
															<a data-toggle="modal" href="#suserEdit"
															onClick="editSuser('${sysUser.createTime}','${sysUser.username}','${sysUser.validTime}','${sysUser.terminalId}','${sysUser.email}','${sysUser.name}','${appUser.status}','${sysUser.uid}','${sysUser.roleId }');"
															class="btn btn-xs btn-primary"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#suserDel"
																onClick="delSuser('${sysUser.uid}','${sysUser.username}','${sysUser.roleId }');"
																class="btn btn-xs btn-danger"><i class="icon-trash"></i></a>
														</c:if>
															
														<c:if test="${sysUser.status == 9}">
															<a data-toggle="modal" href="#"
															onClick=""
															class="btn btn-xs btn-gray"><i class="icon-edit"></i></a>
															<a data-toggle="modal" href="#"
																onClick=""
																class="btn btn-xs btn-gray"><i class="icon-trash"></i></a>
														</c:if>
														</td>
													</tr>
												</c:forEach>

												<c:if test="${page.totalRowNum>0}">
													<c:if test="${page.totalRowNum >= pageSize}">
														<tr class="page_c">
															<td colspan="9">${page.display}</td>
														</tr>
													</c:if>
												</c:if>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /span -->
							</div>
							<!-- /row -->
							<div
								style="display: inline-block; background-repeat: no-repeat; border-width: 4px; font-size: 13px; line-height: 1.39; padding: 4px 9px; background-color: #307ECC;">
								<a href="#modal-table" role="button"
									style="text-decoration: none; color: #fff; font-family: 'Open Sans';"
									data-toggle="modal">添加</a>
							</div>
							<div class="hr hr-18 dotted hr-double"></div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
    </div>
    </div>
</body>

    <!-- END BODY -->
</html>
