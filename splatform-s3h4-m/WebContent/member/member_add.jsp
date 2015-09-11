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
		<form method="post" id="addForm" name="addForm" 
						action="<spring:url value='/memberAdd.do' htmlEscape='true'/>" target="_self">
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="name">会员姓名</label>
    					<input type="text" class="form-control" datatype="*" nullmsg="请输入会员姓名" id="name" name="name" placeholder="请输入会员姓名">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="mobile">手机号码</label>
    					<input type="text" class="form-control" datatype="m"
    					 nullmsg="手机号码必填" errormsg="请填写正确的手机号码" id="mobile" name="mobile" placeholder="请输入手机号码">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			<div class="row">
				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
  						<label for="name">会员性别</label>
  						<div style="height:34px;padding:6px 12px;">
  							<input type="radio" name="sex" checked value="0"> 男
  							<input type="radio" name="sex" value="1"> 女
  						</div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="birthday">出生日期</label>
    					<input id="birthday" class="form-control" type="text"
									name="birthday" value="" placeholder="请输入出生日期" readonly>
  					</div>
  				</div>
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="email">会员邮箱</label>
    					<input id="email" class="form-control" type="email" datatype="*0-0|e"
									name="email" value="" placeholder="请输入会员邮箱">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="point">会员积分</label>
    					<input id="point" class="form-control" type="text" datatype="n1-8" nullmsg="请填写积分"
									name="point" value="" placeholder="请输入会员积分">
						<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
			</div>
			<div class="row">
  				
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group" >
    					<label for="memberLevel">会员等级</label>
    					<select class="form-control" name="memberLevel" id="memberLevel">
  							<option value="1">普通会员</option>
  							<option value="2">钻石会员</option>
  							<option value="3">皇冠会员</option>
						</select>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="address">绑定会员卡</label>
    					<select class="remote-data form-control" id="cardNum" name="vipcards[0].cardNum">
  							<option value="" selected="selected">请选择会员卡</option>
						</select>
  					</div>
  				</div>	
  			</div>	
  			<div class="row">
  				<div class="col-xs-12 col-md-12">
  					<div class="form-group">
    					<label for="address">联系地址</label>
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
<script type="text/javascript"	src="<%=path %>/static/js/select2/select2.full.min.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/zh-CN.js"></script>
<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#birthday').datepicker({format:"yyyy-mm-dd"});
				var $form = null;
				$(document).ready(function() {
					$.ajax({  
						type:'get',  
						url:'unbindCards.do',  
						data:{},  
						cache:true,  
						dataType:'json',  
					    success:function(data){  
							 $(data).each(function(index,item){
								 $("#cardNum").append("<option value='"+item[1]+"'>"+item[1]+"</option>");
								 $('.remote-data').select2({
								});
							 })
						},  
						error:function(){}  
					}); 
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