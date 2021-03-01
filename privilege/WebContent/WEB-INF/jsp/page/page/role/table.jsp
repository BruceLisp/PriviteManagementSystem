<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://666666.com" prefix="p" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：角色信息 &rarr; <span>角色信息查看</span>
	</div>
	
	<table class="table" style="margin-top: 20px;">
		<tr>
  
			<th style="width:10%;">序号</th>
			<th style="width:15%">角色名称</th>
			<th style="width:30%">角色说明</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${list }" var="role" varStatus="in">
			<tr>
				<td align="center">${in.index+1}</td>
				<td align="center">${role.name }</td>
				<td align="center">${role.description }</td>
				<td align="center">
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/roleAction.action?method=editUI') }"  onclick="editUI(${role.id})" clazz="modify" title="修改">&#xe63b;修改</p:div>
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/roleAction.action?method=del') }"  onclick="del(${role.id})" clazz="del" title="删除">&#xe792;删除</p:div>
	                 <p:div test="${loginUser.hasPrivilegeByUrl('/roleAction.action?method=privilegeUI') }"  onclick="privilegeUI(${role.id})" clazz="privilege" title="删除">&#xe641;权限管理</p:div>	                 
	                 <script type="text/javascript">
	                 	function del(id){
	                 		if(confirm("你确认删除吗？")){
	                 			location.href="${pageContext.request.contextPath}/roleAction.action?method=del&id="+id;
	                 		}
	                 	}
	                 	function editUI(id){
	                 		location.href="${pageContext.request.contextPath}/roleAction.action?method=editUI&id="+id;
	                 	}
	                 	function privilegeUI(id){
	                 		location.href="${pageContext.request.contextPath}/roleAction.action?method=privilegeUI&id="+id;
	                 	}
	                 </script>
	            </td>
			<tr>
		</c:forEach>
		<tr>
			<td colspan="6" valign="middle" style="background:#F6F6F6">
				<p:div test="${loginUser.hasPrivilegeByUrl('/roleAction.action?method=addUI') }"  onclick="addUI()" clazz="add" title="添加">&#xe607;添加</p:div>
				<script type="text/javascript">
					function addUI(){
						location.href="${pageContext.request.contextPath}/roleAction.action?method=addUI";
					}
				</script>
			</td>
		</tr>
	</table>
</body>
</html>
