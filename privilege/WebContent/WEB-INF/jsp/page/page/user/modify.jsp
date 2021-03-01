<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户修改</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：用户管理 &rarr; <span>修改用户信息</span>
	</div>
		<div class="modify-page">
			<div class="modify-page-top">修改用户</div>
			<form id="form" action="${pageContext.request.contextPath}/userAction.action">
				<input type="hidden" name="method" value="edit" />
				<input type="hidden" name="id" value="${user.id }" /> 
				<div class="modify-page-form">
					<div class="modify-page-input">
						<span>用户名</span>
						<input type="text" name="name" value="${user.name }" />
					</div>
					<div class="modify-page-input">
						<span>岗位</span>
						<c:forEach items="${roles }" var="role">
							<c:choose>
								<c:when test="${role.checked }">
									<i>${role.name }</i><input type="checkbox" checked="checked" name="roleId" value="${role.id }" />			
								</c:when>
								<c:otherwise>
									<i>${role.name }</i><input type="checkbox" name="roleId" value="${role.id }" />
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
						
					</div>
					<div class="modify-page-input">
						<span>手机号</span>
						<input type="text" name="phone" value="${user.phone }" />
					</div>
				</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save();">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function save(){
						$("#form").submit();
					}
					function back(){
						history.back();
					}
				</script>
			</div>
		</div>
		<script type="text/javascript">
			//自定义校验方法   第一个参数是校验规则的名字    function value代表输入的值 element代表节点标签 param代表校验的参数
			jQuery.validator.addMethod("phone", function(value,element,param) {
				var reg = new RegExp(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/);
				if(reg.test(value)){
					return true;
				} else {
					return false;
				}
			});
			$(function(){
				$("#form").validate({
					rules:{
						name:{
							required:true
						},
						roleId:{
							required:true
						},
						phone:{
							required:true,
							phone:true
						}
						
						
					},
					messages:{
						name:{
							required:"账户名不能为空"
						},
						roleId:{
							required:"必须分配角色信息"
						},
						phone:{
							required:"手机号不能为空",
							phone:"手机格式错误"
						}
					}
				});
			});
		</script>
	</body>
</html>
