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
		您现在所在的位置：图书管理 &rarr; <span>图书预借管理</span>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:10%;">预借单号</th>
			<th style="width:18%">图书ID</th>
			<th style="width:14%">图书名字</th>			
			<th style="width:18%">借书人ID</th>
			<th style="width:10%;">预借日期</th>
			<th style="width:10%">状态</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${records }" var="record">
			<tr id="tr${record.id }">
				<td align="center">${record.id }</td>
				<td align="center">${record.book.id }</td>
				<td align="center">${record.book.name }</td>
				<td align="center">${record.uid }</td>
				<td align="center">${record.lenddate }</td>
				<td align="center" id="td${record.id }">${record.book.statusStr }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=takeBook') }"  onclick="takeBook(${record.id},${record.book.id})" clazz="modify" title="取书">&#xe63b;取书</p:div>
	            </td>
			</tr>
		</c:forEach>
		<tr>
		<!-- 当没有记录时需要进行一些细节处理 -->
			<td colspan="7" valign="middle" style="background:#F6F6F6"></td>
		</tr>
	</table>
<script>
	function takeBook(rid,bid){
		//取书操作
		if(confirm("确定该用户已取书成功？")){
			$.ajax({
				//请求的路径
				url:"${pageContext.request.contextPath}/bookAction.action",
				//提交方式
				type:"post",
				//提交的参数
				data:{
					"method":"takeBook",
					"rid":rid,
					"bid":bid
				},
				//响应数据的格式
				dataType:"text",
				//响应成功返回的数据
				success:function(data){
					if(data=='true'){
						alert("取书成功！");
						//修改前台页面style
						$("#td"+rid).html("已被借阅");
						$("#tr"+rid).html("");
					}else{
						alert("取书失败！请重试！");
					}
				}
			});
		}
	}
</script>
</body>
</html>
