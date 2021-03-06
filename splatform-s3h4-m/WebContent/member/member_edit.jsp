<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp"%>
 <link rel="stylesheet" href="<%=hpath %>/static/css/validcss.css" />
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
						action="<spring:url value='/memberEdit.do' htmlEscape='true'/>" target="_self">
						<input type="hidden" id="id" name="id"
											value="${member.id }" />
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="name">会员姓名</label>
    					<input type="text" class="form-control" datatype="*" nullmsg="请输入会员姓名" id="name" name="name" placeholder="请输入会员姓名" value="${member.name }">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
  						<label for="name">会员性别</label>
  						<div style="height:34px;padding:6px 12px;">
  							<input type="radio" name="sex" <c:if test="${ member.sex eq '0'}">checked</c:if> value="0"> 男
  							<input type="radio" name="sex" <c:if test="${ member.sex eq '1'}">checked</c:if> value="1"> 女
  						</div>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="birthday">出生日期</label>
    					<input id="birthday" class="form-control" type="text" value="${member.birthday }"
									name="birthday" value="" placeholder="请输入出生日期" readonly>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="mobile">手机号码</label>
    					<input type="text" class="form-control" datatype="m" value="${member.mobile }"
    					 nullmsg="手机号码必填" errormsg="请填写正确的手机号码" id="mobile" name="mobile" placeholder="请输入手机号码">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="email">会员邮箱</label>
    					<input id="email" class="form-control" type="email" datatype="*0-0|e" value="${member.email }"
									name="email" value="" placeholder="请输入会员邮箱">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="status">会员状态</label>
    					<select class="form-control" name="status" id="status">
    						<option value="0" <c:if test="${ member.status eq '0'}">selected</c:if>>正常</option>
  							<option value="1" <c:if test="${ member.status eq '1'}">selected</c:if>>锁定</option>
  							<option value="2" <c:if test="${ member.status eq '2'}">selected</c:if>>挂失</option>
  							<option value="3" <c:if test="${ member.status eq '3'}">selected</c:if>>过期</option>
						</select>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardNum">会员卡号</label>
    					<input id="cardNum" class="form-control" type="text" datatype="s6-20" nullmsg="会员卡号必填" errormsg="请输入6-20个字符" value="${member.cardNum }"
									name="cardNum" value="" placeholder="请输入会员卡号">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group" >
    					<label for="memberLevel">会员等级</label>
    					<select class="form-control" name="memberLevel" id="memberLevel">
  							<option value="1" <c:if test="${ member.memberLevel eq '1'}">selected</c:if>>普通卡</option>
  							<option value="2" <c:if test="${ member.memberLevel eq '2'}">selected</c:if>>白金卡</option>
  							<option value="3" <c:if test="${ member.memberLevel eq '3'}">selected</c:if>>钻石卡</option>
						</select>
  					</div>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="point">会员积分</label>
    					<input id="point" class="form-control" type="text" datatype="n1-8" nullmsg="请填写积分" value="${member.point }"
									name="point" value="" placeholder="请输入会员积分">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="balance">卡内余额</label>
    					<input type="text" class="form-control" id="balance" name="balance" datatype="/^[0-9]+(\.[0-9]{2})?$/i" nullmsg="金额必填" errormsg="请输入正确的金额" value="${member.balance }"
    					 placeholder="请输入卡内余额">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardCreated">办卡日期</label>
    					<input id="cardCreated" class="form-control" type="text" value="${member.cardCreated }"
									name="cardCreated" value="" placeholder="请输入办卡日期" readonly>
						
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardDeadline">有效日期</label>
    					<input id="cardDeadline" class="form-control" type="text" value="${member.cardDeadline }"
									name="cardDeadline" value="" placeholder="请输入有效日期" readonly>
  					</div>
  				</div>
  			</div>	
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardPassword">会员卡密码</label>
    					<input id="cardPassword" class="form-control" type="password" value="${member.cardPassword }"
									name="cardPassword" value="" placeholder="会员卡密码">
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="address">联系地址</label>
    					<input type="text" class="form-control" id="address" name="address" value="${member.address }" placeholder="请输入详细地址">
  					</div>
  				</div>	
			</div>
		</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->

<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datepicker.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/Validform_v5.3.2.js"></script>
<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#birthday').datepicker({format:"yyyy-mm-dd"});
				$('#cardCreated').datepicker({format:"yyyy-mm-dd"});
				$('#cardDeadline').datepicker({format:"yyyy-mm-dd"});
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