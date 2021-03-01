<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户添加</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：用户管理 &rarr; <span>添加用户信息</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加用户</div>
			<form id="form" action="${pageContext.request.contextPath }/userAction.action">
				<input type="hidden" name="method" value="add" />
				<div class="add-page-form">
					<div class="add-page-input">
						<span>用户名</span>
						<input type="text" name="name" />
					</div>
					<div class="add-page-input">
						<span>岗位</span>
						<c:forEach items="${list }" var="role">
							<i>${role.name }</i>
							<input type="checkbox" name="roleId" value="${role.id }" />
						</c:forEach>
						<!-- 
						<i>管理员</i><input type="checkbox"  />
						<i>客服</i><input type="checkbox"  /> -->
					</div>
					<div class="add-page-input">
						<span>手机号</span>
						<input type="text" name="phone" />
					</div>
					<div class="add-page-input">
						<span>密码</span>
						<input type="password" name="pwd"/>
					</div>
					<div class="add-page-input">
						<span>密码确认</span>
						<input type="password" name="submit_pwd" />
					</div>
				</div>
			</form>
			<div class="add-page-btn">
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
						},
						pwd:{
							required:true,
							rangelength:[4,8] 
						},
						submit_pwd:{
							required:true,
							rangelength:[4,8],
							equalTo:"input[name='pwd']"
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
						},
						pwd:{
							required:"密码不能为空",
							rangelength:"密码的长度必须在4-8范围内"
						},
						submit_pwd:{
							required:"密码不能为空",
							rangelength:"密码的长度必须在4-8范围内",
							equalTo:"密码两次输入不一致"
						}
					}
				});
			});
		</script>
	</body>
</html>
