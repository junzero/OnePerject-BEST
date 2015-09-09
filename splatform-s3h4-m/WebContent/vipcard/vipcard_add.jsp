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
		<form method="post" id="addForm" name="addForm" 
						action="<spring:url value='/memberAdd.do' htmlEscape='true'/>" target="_self">
			
			
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardNum">会员卡号</label>
    					<input id="cardNum" class="form-control" type="text" datatype="s6-20" nullmsg="会员卡号必填" errormsg="请输入6-20个字符"
									name="cardNum" value="" placeholder="请输入会员卡号">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group" >
    					<label for="type">卡类别</label>
    					<select class="form-control" name="type" id="type">
  							<option value="1">普通卡</option>
  							<option value="2">白金卡</option>
  							<option value="3">钻石卡</option>
						</select>
  					</div>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardPassword">会员卡密码</label>
    					<input id="cardPassword" class="form-control" type="password"
									name="cardPassword" value="" placeholder="会员卡密码">
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="balance">卡内金额</label>
    					<input type="text" class="form-control" id="balance" name="balance" datatype="/^[0-9]+(\.[0-9]{2})?$/i" nullmsg="金额必填" errormsg="请输入正确的金额"
    					 placeholder="请输入卡内余额">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardCreated">开通日期</label>
    					<input id="cardCreated" class="form-control" type="text"
									name="cardCreated" value="" placeholder="请输入办卡日期" readonly>
						
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardDeadline">有效日期</label>
    					<input id="cardDeadline" class="form-control" type="text"
									name="cardDeadline" value="" placeholder="请输入有效日期" readonly>
  					</div>
  				</div>
  			</div>	
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardPassword">会员姓名</label>
    					<input id="cardPassword" class="form-control" type="password"
									name="cardPassword" value="" placeholder="会员卡密码">
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="address">手机号码</label>
    					<input type="text" class="form-control" id="address" name="address" placeholder="请输入详细地址">
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
			 		$form = $('#addForm').Validform({tiptype:function(msg,o,cssctl){
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