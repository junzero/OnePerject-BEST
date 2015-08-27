<%@page import="com.sh.manage.constants.*"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String lpath=this.getServletContext().getContextPath();
%>

<!-- MENU SECTION -->
       <div id="left" >
            <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img class="media-object img-thumbnail user-img" alt="User Picture" src="<%=lpath %>/static/assets/img/user1.jpg" />
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"><%=session.getAttribute("name")%></h5>
                    <ul class="list-unstyled user-info">
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> 在线
                        </li>
                    </ul>
                </div>
                <br />
            </div>

            <ul id="menu" class="collapse">
                 <%-- <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#component-nav">
                        <i class="icon-tasks"> </i> 系统设置
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                       &nbsp; <span class="label label-default">10</span>&nbsp;
                    </a>
                    <ul class="in" id="component-nav" style="height:auto">                       
                        <li class=""><a href="<%=lpath %>/gmanage.do"><i class="icon-angle-right"></i> 组织管理 </a></li>
                        <li class=""><a href="<%=lpath %>/romanage.do"><i class="icon-angle-right"></i> 角色管理 </a></li>
                        <li class=""><a href="<%=lpath %>/umanage.do"><i class="icon-angle-right"></i> 用户管理 </a></li>
                        <li class=""><a href="<%=lpath %>/aumanage.do"><i class="icon-angle-right"></i> 会员管理 </a></li>
                    </ul>
                </li> --%>
                
                
                <c:forEach items="${sessionScope.treeNodeList }" var="treeNode">
	                <li class="panel ">
		                <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" 
		                data-target="#form-${treeNode.code}-nav">
		                    <i class="${treeNode.iconTag }"></i> ${treeNode.name }
		                    <span class="pull-right">
		                        <i class="icon-angle-left"></i>
		                    </span>
		                    &nbsp; <span class="label label-success">5</span>&nbsp;
		                </a>
		                <ul class="in" id="form-${treeNode.code}-nav" style="height:auto;">
		                	<c:if test="${treeNode.hasChild == 1}">
		                		<!-- has child nodes -->
		                		<c:forEach items="${treeNode.children}" var="childNode">
		                			<li class="">
		                				<a href="<%=lpath %>/${childNode.menuUrl }"><i class="icon-angle-right"></i> ${childNode.name } </a>
		                			</li>
		                		</c:forEach>
		                	</c:if>
		                </ul>
		            </li>
                </c:forEach>
                
                
            </ul>

        </div>