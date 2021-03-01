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
				location.href="${pageContext.request.contextPath }/bookAction.action?method=search&mess="+value;
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:10%;">书号</th>
			<th style="width:18%">书名</th>
			<th style="width:14%">作者</th>			
			<th style="width:18%">出版社</th>
			<th style="width:10%;">类别</th>
			<th style="width:10%">状态</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${books }" var="book">
			<tr>
				<td align="center">${book.id }</td>
				<td align="center">${book.name }</td>
				<td align="center">${book.author }</td>
				<td align="center">${book.productor }</td>
				<td align="center">${book.typeStr }</td>
				<td align="center">${book.statusStr }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=editUI') }"  onclick="editUI(${book.id})" clazz="modify" title="修改">&#xe63b;修改</p:div>
					 <p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=del') }"  onclick="del(${book.id})" clazz="del" title="删除">&#xe792;删除</p:div>
	            </td>
			<tr>
		</c:forEach>
		<tr>
			<td colspan="7" valign="middle" style="background:#F6F6F6">
				<p:div test="${loginUser.hasPrivilegeByUrl('/bookAction.action?method=addUI') }"  onclick="addUI()" clazz="add" title="添加">&#xe607;添加</p:div>
				<script type="text/javascript">
					function addUI(){
						location.href="${pageContext.request.contextPath}/bookAction.action?method=addUI";
					}
				</script>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	function del(id){
		if(confirm("你确认删除吗？")){
			location.href="${pageContext.request.contextPath}/bookAction.action?method=del&id="+id;
		}
	}
	function editUI(id){
		location.href="${pageContext.request.contextPath}/bookAction.action?method=editUI&id="+id;
	}
</script>
</html>
