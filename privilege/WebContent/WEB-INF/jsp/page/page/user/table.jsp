<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://666666.com"  prefix="p"%>
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
		您现在所在的位置：用户信息 &rarr; <span>用户信息查看</span>
	</div>
	<div class="searchdiv">		
		<div>用户名：<input id="name" type="text" value="${param.name }"/></div>
		<div class="btn1" onclick="search()">查 找</div>
		<p:div test="${loginUser.hasPrivilegeByUrl('/userAction.action?method=addUI') }" clazz="btn1" onclick="add()">添加</p:div>
		<script type="text/javascript">
			function search(){
				var value = $("#name").val();
				location.href="${pageContext.request.contextPath }/userAction.action?name="+value;
			}
			function add(){
				location.href="${pageContext.request.contextPath }/userAction.action?method=addUI";
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:50px;">编号</th>
			<th style="width:150px;">用户名</th>
			<th style="width:100px;">角色</th>
			<th style="width:100px;">手机号</th>
			<th style="width:100px;">操作</th>
		</tr>
		<c:forEach items="${list }" var="user" varStatus="in">
			<tr>
				<td align="center">${in.index+1 }</td>
				<td align="center">${user.name }</td>
				<td align="center">
					<c:forEach items="${user.roles }" var="role">
						${role.name }&nbsp;&nbsp;
					</c:forEach>
				</td>
				<td align="center">${user.phone }</td>
				<td align="center">
	               	<p:div test="${loginUser.hasPrivilegeByUrl('/userAction.action?method=editUI') }" onclick="editUI(${user.id})" clazz="modify" title="修改">&#xe63b;修改</p:div>
	                <p:div test="${loginUser.hasPrivilegeByUrl('/userAction.action?method=del') }" onclick="del(${user.id});" clazz="del" title="删除">&#xe792;删除</p:div>
	                <p:div test="${loginUser.hasPrivilegeByUrl('/userAction.action?method=resetPwd') }" onclick="resetPwd(${user.id});" clazz="modify" title="修改">&#xe63b;重置密码</p:div>
	                <script type="text/javascript">
	                	function del(id){
	                		if(confirm("你确认删除吗？")){
	                			location.href="${pageContext.request.contextPath}/userAction.action?method=del&id="+id;
	                		}
	                	}
	                	function editUI(id){
	                		location.href="${pageContext.request.contextPath}/userAction.action?method=editUI&id="+id;
	                	}
	                	function resetPwd(id){
	                		if(confirm("你确认将密码重置为1234？")){
	                			location.href="${pageContext.request.contextPath}/userAction.action?method=resetPwd&id="+id;
	                		}
	                	}
	                </script>
	            </td>
			<tr>
       </c:forEach>
		<!-- <tr>
			<td colspan="6" valign="middle" align="center" style="background:#F6F6F6">
			</td>
		</tr> -->
	</table>
</body>
</html>
