<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>

<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp"%>
 <link rel="stylesheet" href="<%=hpath %>/static/css/validcss.css" />
  <link rel="stylesheet" href="<%=hpath %>/static/js/select2/select2.min.css" />
  <link rel="stylesheet" href="<%=path %>/static/js/date-time/bootstrap-datetimepicker.css"/>
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
						action="<spring:url value='/visitRecordEdit.do' htmlEscape='true'/>" target="_self">
						<input type="hidden" name="id" value="${visitRecord.id }"/>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="visitorName">来访人</label>
    					<input type="text" class="form-control"
    					value="${visitRecord.visitorName }" datatype="s1-10" nullmsg="请输入来访人姓名" id="visitorName" name="visitorName" placeholder="请输入来访人姓名">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="visitedName">被访人</label>
    					<input type="text" class="form-control"
    					value="${visitRecord.visitedName }" datatype="s1-10" nullmsg="请输入被访人姓名" id="visitedName" name="visitedName" placeholder="请输入被访人姓名">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>
			</div>
			<div class="row">
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="visitTime">来访日期</label>
    					<input id="visitTime" class="form-control" type="text" value="${visitRecord.visitTime }"
									name="visitTime" value="" placeholder="请输入来访日期" readonly>
  					</div>
  				</div>
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="leaveTime">离开时间</label>
    					<input id="leaveTime" class="form-control" type="text" value="${visitRecord.leaveTime }"
									name="leaveTime" value="" placeholder="请输入离开时间" readonly>
  					</div>
  				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="mobile">手机号码</label>
    					<input type="text" class="form-control" datatype="m" value="${visitRecord.mobile }"
    					 nullmsg="手机号码必填" errormsg="请填写正确的手机号码" id="mobile" name="mobile" placeholder="请输入手机号码">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
  				<div class="col-xs-6 col-md-6">
  					<div class="form-group">
    					<label for="idcard">证件号码</label>
    					<input type="text" class="form-control" datatype="s0-20" value="${visitRecord.idcard }"
    							id="idcard" name="idcard" placeholder="请输入证件号码">
    					<div class="info"><span class="Validform_checktip Validform_wrong"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  				</div>	
			</div>
			<div class="row">
  				<div class="col-xs-12 col-md-12">
  					<div class="form-group">
  						<label for="reason">来访事由</label>
  						<textarea rows="3" cols="1" class="form-control" datatype="s0-500" name="reason" id="reason" placeholder="请输入来访事由">${visitRecord.reason }</textarea>
  					</div>
  				</div>
  				
  			</div>	
  			
		</form>
	</div>
	<!--END MAIN WRAPPER -->
</body>
<!-- GLOBAL SCRIPTS -->

<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/select2.full.min.js"></script>
<script type="text/javascript"	src="<%=path %>/static/js/select2/zh-CN.js"></script>
<iframe name="targetFrame" style="width: 0%; display: none;"></iframe>
<script type="text/javascript">
				$('#visitTime').datetimepicker({language:'zh-CN',format:"yyyy-mm-dd",minView: 2,autoClose:2});
				$('#leaveTime').datetimepicker({language:'zh-CN',format:"hh:ii:ss",startView: 1,autoClose:2,
					minView: 0,
					maxView: 1,});
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
								 $("#cardNum").append("<option value='"+item.cardNum+"'>"+item.cardNum+"</option>");
								 $('.remote-data').select2({
								});
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