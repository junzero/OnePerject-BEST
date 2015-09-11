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
						action="<spring:url value='/vipcardEdit.do' htmlEscape='true'/>" target="_self">
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="cardNum">会员卡号</label>
    					<input id="cardNum" class="form-control" type="text" datatype="s6-20" nullmsg="会员卡号必填" errormsg="请输入6-20个字符" ajaxurl="validCardNum.do?id=${vipcard.id }" 
									name="cardNum" value="${vipcard.cardNum }" placeholder="请输入会员卡号">
						<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group" >
    					<label for="type">卡类别</label>
    					<select class="form-control" name="type" id="type">
  							<option value="1" <c:if test="${vipcard.type eq '1' }">selected</c:if>>普通卡</option>
  							<option value="2" <c:if test="${vipcard.type eq '2' }">selected</c:if>>白金卡</option>
  							<option value="3" <c:if test="${vipcard.type eq '3' }">selected</c:if>>钻石卡</option>
						</select>
  					</div>
  				</div>
  			</div>
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="openTime">开通日期</label>
    					<input id="openTime" class="form-control" type="text"
								value="${vipcard.openTime }" name="openTime" value="" placeholder="请输入开通日期" readonly>
						
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="deadline">有效日期</label>
    					<input id="deadline" class="form-control" type="text"
									value="${vipcard.deadline }" name="deadline" value="" placeholder="请输入有效日期" readonly>
  					</div>
  				</div>
  			</div>	
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="password">会员卡密码</label>
    					<input id="password" class="form-control" type="text"  datatype="s0-10"
								value="${vipcard.password }"	name="password" placeholder="会员卡密码">
								<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="balance">卡内金额</label>
    					<input type="text" class="form-control" id="balance" name="balance" datatype="/^[0-9]+(\.[0-9]{2})?$/i" nullmsg="金额必填" errormsg="请输入正确的金额"
    					 value="${vipcard.balance }" placeholder="请输入卡内金额额">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			
  			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group" >
    					<label for="status">卡状态</label>
    					<select class="form-control" name="status" id="status">
  							<option value="0" <c:if test="${vipcard.status eq '0' }">selected</c:if>>未开通</option>
  							<option value="1" <c:if test="${vipcard.status eq '1' }">selected</c:if>>已开通</option>
  							<option value="2" <c:if test="${vipcard.status eq '2' }">selected</c:if>>注销</option>
  							<option value="3" <c:if test="${vipcard.status eq '3' }">selected</c:if>>冻结</option>
						</select>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="address">绑定会员</label>
    					<select class="remote-data form-control" id="memberId" name="member.id">
  							<option value="">请选择会员</option>
						</select>
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
<script type="text/javascript"	src="<%=path %>/static/js/select2/select2.full.min.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/zh-CN.js"></script>

<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
		
				$('#openTime').datepicker({format:"yyyy-mm-dd"});
				$('#deadline').datepicker({format:"yyyy-mm-dd"});
				
				var $form = null;
				$(document).ready(function() {
					$.ajax({  
						type:'get',  
						url:'unbindMembers.do',  
						data:{memberId:'${vipcard.member.id}'},  
						cache:true,  
						dataType:'json',  
					    success:function(data){  
							 $(data).each(function(index,item){
								 $("#memberId").append("<option value='"+item[0]+"'>"+item[1]+"</option>");
								 var $select = $('.remote-data').select2({
								});
								 $('.remote-data').val("${vipcard.member.id}").trigger("change");
							 })
						},  
						error:function(){}  
					}); 
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