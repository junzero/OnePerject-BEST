<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/include.jsp"%>


<!-- include HEAD -->
<!-- BEGIN HEAD -->
<%@ include file="../main/header.jsp" %>
<style type="text/css">
.dl-horizontal dt {
    float: left;
    width: 80px;
    overflow: hidden;
    clear: left;
    text-align: right;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.dl-horizontal dd {
    margin-left: 100px;
}
blockquote {
    padding: 5px 10px;
    margin: 0 0 10px 25px;
    border-left: 5px solid #1b809e;
    width:80%;
    background: #fff;
}
blockquote p {
    font-size: 14px;
    font-weight: 400;
    line-height: 1.25;
    margin: 0 0 5px;
}
blockquote footer, blockquote small {
    display: block;
    font-size: 80%;
    line-height: 1.42857143;
    color: #777;
}
</style>
<script type="text/javascript">
</script>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>

	<dl class="dl-horizontal">
      <dt>会员卡号</dt>
      <dd>${record.cardNum}</dd>
      <dt>会员姓名</dt>
      <dd>${record.memberName} </dd>
      <dt>消费总额</dt>
      <dd>${record.totalPrice}</dd>
      <dt>获得积分</dt>
      <dd>${record.point}</dd>
      <dt>消费时间</dt>
      <dd>${record.buyTime}</dd>
       <dt>消费细项</dt><br />
      <c:forEach items="${record.items}" var="i">
      	<blockquote>
  			<p>${i.goodsName }</p>
  			<footer>数量：${i.quantity} 价格：${i.pirce }</footer>
		</blockquote>
      </c:forEach>
    </dl>	
  				
</body>
</html>
