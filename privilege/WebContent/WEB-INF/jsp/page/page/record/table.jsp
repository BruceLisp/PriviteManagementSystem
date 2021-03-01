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
		您现在所在的位置：图书管理 &rarr; <span>图书预借信息查看</span>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:10%;">书号</th>
			<th style="width:18%">书名</th>
			<th style="width:14%">作者</th>			
			<th style="width:18%">出版社</th>
			<th style="width:10%;">借书时间</th>
			<th style="width:10%">状态</th>
		</tr>
		<c:forEach items="${records }" var="record">
			<tr>
				<td align="center">${record.book.id }</td>
				<td align="center">${record.book.name }</td>
				<td align="center">${record.book.author }</td>
				<td align="center">${record.book.productor }</td>
				<td align="center">${record.period }天</td>
				<td align="center">${record.book.statusStr }</td>
				
			<tr>
		</c:forEach>
		<tr>
		<!-- 当没有记录时需要进行一些细节处理 -->
			<td colspan="7" valign="middle" style="background:#F6F6F6"></td>
		</tr>
	</table>
	
</body>
</html>
