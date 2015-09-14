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
	.item-row{
		margin-bottom:10px;
	}
</style>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<div id="wrap">
		<form method="post" id="editForm" name="editForm"
						action="<spring:url value='/buyRecordEdit.do' htmlEscape='true'/>" target="_self">
						<input type="hidden" name="id" value="${buyRecord.id }"/>
					<div class="row">
  					<div class="form-group">
    					<label for="cardNum">会员选择</label>
    					<select class="select2 form-control" id="cardNum" name="cardNum" datatype="s1-20" nullmsg="请选择会员">
  							<option value="" selected="selected">请选择会员</option>
						</select>
						<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  					</div>
  					</div>
  					<div class="row">
  						<label for="cardNum">消费细项</label>
  					</div>
  					<c:forEach items="${buyRecord.items }" var="item" varStatus="s">
  					<div class="row item-row" id="row${s.index }">
  						<input type="hidden" name="items[${s.index }].id"/>
  						<div class="col-xs-6 col-md-6" style="padding-left:0px">
    						<select class="select2 row-select2 form-control" name="items[${s.index }].goodsId" id="item-select${s.index }" datatype="s1-20" nullmsg="请选择消费细项">
  								<option value="" selected="selected">请选择消费项目</option>
							</select>
							<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  						</div>
  						<div class="col-xs-4 col-md-4">
  							<input type="text" class="form-control" name="items[${s.index }].quantity" placeholder="请输入消费数量" datatype="n1-3,/^[1-9]\d*$/i" errormsg="请填写大于0的整数" value="${item.quantity }" nullmsg="消费数量必填"/>
  							<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
  						</div>
  						<div class="col-xs-2 col-md-2">
  							<button type="button" name="del-item-btn" class="btn btn-danger" onclick='delItemRow("${s.index}")'>删除</button>
  						</div>
  					</div>
  					</c:forEach>
  					<div class="row">
  						<button type="button" class="btn btn-info btn-sm" onclick="addItemRow()">继续添加</button>
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
				function addItemRow(){
					var itemCount = $(".item-row").length;
					var rowHtml = "<div class='row item-row' id='row"+itemCount+"'>"
						+"<div class='col-xs-6 col-md-6' style='padding-left:0px'>"
						+"<select class='select2 row-select2 form-control' name='items["+itemCount+"].goodsId' id='item-select"+itemCount+"' datatype='s1-20' nullmsg='请选择消费细项'>"
								+"<option value='' selected='selected'>请选择消费项目</option>"
						+"</select>"
						+"<div class='info'><span class='Validform_checktip'></span><span class='dec'><s class='dec1'>&#9670;</s><s class='dec2'>&#9670;</s></span></div>"
						+"</div>"
						+"<div class='col-xs-4 col-md-4'>"
							+"<input type='text' class='form-control' name='items["+itemCount+"].quantity' placeholder='请输入消费数量' datatype='n1-3' nullmsg='消费数量必填'/>"
							+"<div class='info'><span class='Validform_checktip'></span><span class='dec'><s class='dec1'>&#9670;</s><s class='dec2'>&#9670;</s></span></div>"
						+"</div>"
						+"<div class='col-xs-2 col-md-2'>"
							+"<button type='button' name='del-item-btn' class='btn btn-danger' onclick='delItemRow("+itemCount+")'>删除</button>"
						+"</div>"
					+"</div>";
					var last = itemCount -1;
					goodsSelect2Init("item-select"+itemCount);
					$("#row"+last).after(rowHtml);
					if($(".item-row").length != 1){
						$(".item-row").eq(0).find("button").eq(0).show();
					}
				}
				
				function delItemRow(id){
					$("#row"+id).remove();
					$(".item-row").each(function(index,row){
						$(this).find("select").eq(0).attr("name","items["+index+"].goodsId");
						if($(this).find("input").length > 1){
							$(this).find("input").eq(0).attr("name","items["+index+"].id");
							$(this).find("input").eq(1).attr("name","items["+index+"].quantity");
						}else{
							$(this).find("input").eq(0).attr("name","items["+index+"].quantity");
						}
						$(this).find("button").eq(0).attr("onclick","delItemRow("+index+")");
						$(this).attr("id","row"+index);
					});	
					if($(".item-row").length == 1){
						$(".item-row").find("button").eq(0).hide();
					}
				}
				var $form = null;
				function goodsSelect2Init(id,isModify,goodsId){
					$.ajax({  
						type:'get',  
						url:'allGoods.do',  
						data:{name:""},  
						cache:true,  
						dataType:'json',  
					    success:function(data){  
							 $(data).each(function(index,item){
								 $("#"+id).append("<option value='"+item.id+"'>"+item.name+"</option>");
							 });
							 $("#"+id).select2({
								});
								 $("#"+id).on("select2:select",function(e){
									 $form.check(false);
								 });
								 $("#"+id).on("select2:unselect",function(e){
									 $form.check(false);
								 })
								  $("#"+id).on("select2:close",function(e){
									 $form.check(false);
								 })
								 if(isModify){
									 $('#'+id).val(goodsId).trigger("change");
								 }
						},  
						error:function(){}  
					}); 
				}
				$(document).ready(function() {
					
					<c:forEach items="${buyRecord.items }" var="item" varStatus="s">
						goodsSelect2Init("item-select${s.index}",true,"${item.goodsId}");
					</c:forEach>
					
					if($(".item-row").length == 1){
						$(".item-row").eq(0).find("button").eq(0).hide();
					}
					$.ajax({  
						type:'get',  
						url:'findByFilter.do',  
						data:{filter:""},  
						cache:true,  
						dataType:'json',  
					    success:function(data){  
							 $(data).each(function(index,item){
								 $("#cardNum").append("<option value='"+item.cardNum+"'>"+item.cardNum+">"+item.member.name+">"+item.member.mobile+"</option>");
								 $('#cardNum').select2({
								});
								 $('#cardNum').on("select2:select",function(e){
									 $form.check(false);
								 });
								 $('#cardNum').on("select2:unselect",function(e){
									 $form.check(false);
								 })
								 $('#cardNum').on("select2:close",function(e){
									 $form.check(false);
								 });
							 });
							 $('#cardNum').val("${buyRecord.cardNum}").trigger("change");
						},  
						error:function(){}  
					}); 
			 		$form = $('#editForm').Validform({tiptype:function(msg,o,cssctl){
						//msg：提示信息;
						//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
						//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							var objtip;
							var infoObj;
							if(o.obj.hasClass("select2")){
								objtip=o.obj.next().next().find(".Validform_checktip");
								infoObj=o.obj.next().next();
							}else{
								objtip=o.obj.next().find(".Validform_checktip");
								infoObj=o.obj.next();
							}
							cssctl(objtip,o.type);
							objtip.html(msg);
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