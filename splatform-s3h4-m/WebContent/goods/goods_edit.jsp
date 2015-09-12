<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp"%>
 <link rel="stylesheet" href="<%=hpath %>/static/css/validcss.css" />
  <link rel="stylesheet" href="<%=hpath %>/static/js/select2/select2.min.css" />
<style type="text/css">
	
	#wrap{
		padding:12px 15px 12px 30px;
	}
	
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
		<form method="post" id="editForm" name="editForm" 
						action="<spring:url value='/goodsEdit.do' htmlEscape='true'/>" target="_self">
						<input type="hidden" name="id" value="${goods.id }"/>
			<div class="form-group">
    					<label for="name">商品名称</label>
    					<input id="name" class="form-control" type="text" datatype="s1-20" nullmsg="商品名称必填" errormsg="请输入6-20个字符" ajaxurl="validCardNum.do" 
									name="name" value="${goods.name }" placeholder="请输入商品名称">
						<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  					<div class="form-group">
    					<label for="price">商品价格</label>
    					<input type="text" class="form-control" id="price" name="price" datatype="/^[0-9]+(\.[0-9]{1,2})?$/i" nullmsg="商品名称价格必填" errormsg="请输入正确的金额"
    					 value="${goods.price} " placeholder="请输入商品价格">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  					<div class="form-group" >
    					<label for="type">商品类别</label>
    					<select class="form-control" name="type" id="type">
  							<option value="1" <c:if test="${goods.type eq '1' }">selected</c:if>>产品</option>
  							<option value="2" <c:if test="${goods.type eq '2' }">selected</c:if>>服务</option>
						</select>
  					</div>
  			
  					<div class="form-group">
    					<label for="description">商品描述</label>
    					<textarea id="description" row="5" class="form-control" type="text"
									name="description" placeholder="请输入商品描述">${goods.description}</textarea>
  					</div>
		</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->

<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/select2.full.min.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/zh-CN.js"></script>

<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
		
				
				var $form = null;
				$(document).ready(function() {
					
			 		$form = $('#editForm').Validform({tiptype:function(msg,o,cssctl){
						//msg：提示信息;
						//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
						//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
						
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							var objtip=o.obj.next().find(".Validform_checktip");
							cssctl(objtip,o.type);
							objtip.html(msg);
							var infoObj=o.obj.next();
							if(o.type==2){
								infoObj.fadeOut(200);
							}else{
								if(infoObj.is(":visible")){return;}
								var left=o.obj.position().left,
									top=o.obj.position().top;
								infoObj.css({
									left:left+70,
									top:top-20
								}).show().animate({
									top:top-35	
								},200);
							}
						}	
					}});
				});
		function check(){
			if($form){
				return $form.check(false);
			}
			return true;
		}
			
</script>    
</html>