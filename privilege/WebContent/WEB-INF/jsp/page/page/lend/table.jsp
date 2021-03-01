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
		您现在所在的位置：图书管理 &rarr; <span>图书信息查看</span>
	</div>
	<div class="searchdiv">		
		<div><input id="mess" type="text" placeholder="书名、作者名、或出版社"/></div>
		<div class="btn1" onclick="search()">查 找</div>
		<script type="text/javascript">
			function search(){
				var value = $("#mess").val();
				location.href="${pageContext.request.contextPath }/bookAction.action?method=lsearch&mess="+value;
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:12%;">书号</th>
			<th style="width:20%">书名</th>
			<th style="width:16%">作者</th>			
			<th style="width:20%">出版社</th>
			<th style="width:12%;">类别</th>
			<th style="width:12%">状态</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${books }" var="book">
			<tr>
				<td align="center">${book.id }</td>
				<td align="center">${book.name }</td>
				<td align="center">${book.author }</td>
				<td align="center">${book.productor }</td>
				<td align="center">${book.typeStr }</td>
				<td align="center" id="td${book.id }" >${book.statusStr }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=reserve') }" other="id = '${book.id }' style='background:${book.status==0?'':'rgb(218,218,218)' }' "
	                    onclick="${book.status==0? 'lend(this.id)':'' }" clazz="modify" title="预借">&#xe63b;预借</p:div>
	            </td>
			<tr>
		</c:forEach>
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
	function lend(id){
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
				"method":"reserve",
				"bid":bid,
				"uid":"${loginUser.id }",
				"period":days
			},
			//响应数据的格式
			dataType:"text",
			//响应成功返回的数据
			success:function(data){
				if(data=='true'){
					alert("预借成功！");
					//修改前台页面style
					$("#"+bid).css('background-color','rgba(218,218,218)');
					$("#"+bid).attr("onclick","&#xe63b;");
					$("#td"+bid).html("已被预借");
					$("#alert").attr("class","hidden");
				}else{
					alert("预借失败！请重试！");
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
</script>
</html>
