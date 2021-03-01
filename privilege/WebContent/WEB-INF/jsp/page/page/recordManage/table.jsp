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
		您现在所在的位置：图书借阅管理 &rarr; <span>图书借阅管理</span>
	</div>
	<div class="searchdiv">		
		<div><input id="mess" type="text" placeholder="书名，书id，或用户id"/></div>
		<div class="btn1" onclick="search()">查 找</div>
		<script type="text/javascript">
			function search(){
				var value = $("#mess").val();
				location.href="${pageContext.request.contextPath }/bookAction.action?method=rsearch&mess="+value;
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:10%;">借书单号</th>
			<th style="width:10%">图书ID</th>
			<th style="width:14%">图书名字</th>			
			<th style="width:10%">借书人ID</th>
			<th style="width:10%">借书人名字</th>
			<th style="width:10%;">预借日期</th>
			<th style="width:6%;">借书时长</th>
			<th style="width:10%;">还书日期</th>
			<th style="width:10%">状态</th>
			<th style="width:10%">操作</th>
		</tr>
		<c:forEach items="${records }" var="record">
			<tr>
				<td align="center">${record.id }</td>
				<td align="center">${record.book.id }</td>
				<td align="center">${record.book.name }</td>
				<td align="center">${record.uid }</td>
				<td align="center">${record.uname }</td>
				<td align="center">${record.lenddate }</td>
				<td align="center">${record.period }天</td>
				<td align="center">${record.deadline }</td>
				<td align="center">${record.book.statusStr }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=returnBook') }"  onclick="returnBook(${record.id},${record.bid})" clazz="modify" title="还书">&#xe63b;还书</p:div>
	            </td>
			</tr>
		</c:forEach>
		<tr>
		<!-- 当没有记录时需要进行一些细节处理 -->
			<td colspan="10" valign="middle" style="background:#F6F6F6"></td>
		</tr>
	</table>
	<script type="text/javascript">
		function returnBook(rid,bid){
			location.href="${pageContext.request.contextPath }/bookAction.action?method=returnBook&rid="+rid+"&bid="+bid;
			alert("还书成功！");
		}
	</script>
</body>
</html>
