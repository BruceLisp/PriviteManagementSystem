<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://666666.com" prefix="p" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：图书管理 &rarr; <span>图书借阅记录信息查看</span>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:10%;">书号</th>
			<th style="width:18%">书名</th>
			<th style="width:14%">作者</th>			
			<th style="width:18%">出版社</th>
			<th style="width:10%;">借书时间</th>
			<th style="width:10%">状态</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${records }" var="record">
			<tr>
				<td align="center">${record.book.id }</td>
				<td align="center">${record.book.name }</td>
				<td align="center">${record.book.author }</td>
				<td align="center">${record.book.productor }</td>
				<td align="center"  id="td${record.book.id }" >${record.period }天</td>
				<td align="center">${record.book.statusStr }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=resetPeriod') }"  onclick="resetTime(${record.book.id})" clazz="modify" title="调整节约时间">&#xe63b;调整时间</p:div>
					 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=cancelReserve') }"  onclick="cancel(${record.book.id},${record.id})" clazz="del" title="取消预借">&#xe792;取消预借</p:div>
	            </td>
			<tr>
		</c:forEach>
		<tr>
		<!-- 当没有记录时需要进行一些细节处理 -->
			<td colspan="7" valign="middle" style="background:#F6F6F6"></td>
		</tr>
	</table>
	<!-- 自定义选择弹框，用于预借时进行图书的接取天数的选择 -->
	<div id="alert" class="hidden">
		<div style="width:300px;height:100px;text-align:center" >
			<h4>请选择你要借取的时间</h4>
			<input id="period" type="text" style="text-align:center" value="30"/>&nbsp;天
 		</div>
 		<div style="width:300px; height:30px;">
 			<div onclick="option(this.id)" id="3" class="confirm" style="float:left;margin-left:30px">3天</div>
 			<div onclick="option(this.id)" id="5" class="confirm" style="float:left;margin-left:10px">5天</div>
 			<div onclick="option(this.id)" id="7" class="confirm" style="float:left;margin-left:10px">7天</div>
 			<div onclick="option(this.id)" id="15" class="confirm" style="float:left;margin-left:10px">15天</div>
 			<div onclick="option(this.id)" id="30" class="confirm" style="float:left;margin-left:10px">30天</div>
 		</div>
 		<div style="width:300px; height:30px; margin-top:20px;">
 			<div class="confirm" onclick="confirm()" style="float:left;margin-left:70px">确任</div>
			<div class="cancel" onclick="cancle()" style="float:right;margin-right:70px">取消</div>
 		</div>
	</div>
</body>
<script type="text/javascript">
	var bid;
	function resetTime(id){
		bid = id;
		//弹框弹出
		$("#alert").attr("class","show");		
	}
	//弹框的确认点击事件，向数据库提交数据，最后关闭弹框
	function confirm(){
		var days = $("#period").val();
		//预借的后台数据操作
		$.ajax({
			//请求的路径
			url:"${pageContext.request.contextPath}/bookAction.action",
			//提交方式
			type:"post",
			//提交的参数
			data:{
				"method":"adjustBStatus",
				"bid":bid,
				"uid":"${loginUser.id }",
				"period":days
			},
			//响应数据的格式
			dataType:"text",
			//响应成功返回的数据
			success:function(data){
				if(data=='true'){
					alert("调整成功！");
					//修改前台页面style
					$("#td"+bid).html(days+"天");
					$("#alert").attr("class","hidden");
				}else{
					alert("调整失败！请重试！");
				}
			}
		});
	}
	//弹框的取消点击事件，隐藏弹框
	function cancle(){
		$("#alert").attr("class","hidden");
	}
	//点击按钮选择天数，并改变输入框的值
	function option(day){
		$("#period").attr("value",day);
	}
	//取消预借按钮的点击事件
	function cancel(bid,rid){
		if(confirm("你确认取消预借吗？")){
			//取消预借操作
			location.href="${pageContext.request.contextPath}/bookAction.action?method=cancelReserve&bid="+bid+"&rid="+rid+"&uid=${loginUser.id }";
		}
	}
</script>
</html>
