<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：个人信息&rarr; <span>修改个人密码</span>
	</div>
		<div class="modify-page" style="margin:0 auto; width:70%;">
			<div class="modify-page-top">修改用户</div>
			<form id="form" action="${pageContext.request.contextPath}/personAction.action" method="post">
				<input type="hidden" name="method" value="updateloginUserPwd"/>
				<div class="modify-page-form">
					<div class="modify-page-input">
						<span>请输入原密码</span>
						<input type="password" name="old_pwd" value="" />
					</div>
					<div class="modify-page-input">
						<span>请填写新密码</span>
						<input type="password" name="new_pwd" value="" />
					</div>
					<div class="modify-page-input">
						<span>再次填写新密码</span>
						<input type="password" name="submit_pwd" value=""/>
					</div>
				</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save();">保存</a>
				<a class="back" href="javascript:back();">返回</a>
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
			jQuery.validator.addMethod("passwordIsTrue", function(value,element,param) {
				var tag = false;
				$.ajax({
					url:"${pageContext.request.contextPath}/personAction.action",
					data:{"method":"queryPwdIsTrue","pwd":value},
					async: false,
	                cache: false,
					success:function(data){
						if(data==1){
							tag = true;
						}
					}
				}); 
				return tag;
			});
		 	$(function(){
				$("#form").validate({
					rules:{
						old_pwd:{
							required:true,
							rangelength:[4,8],
							passwordIsTrue:true
						},
						new_pwd:{
							required:true,
							rangelength:[4,8]
						},
						submit_pwd:{
							required:true,
							rangelength:[4,8],
							equalTo:"input[name='new_pwd']"
						}
						
					},
					messages:{
						old_pwd:{
							required:"原来密码不能为空",
							rangelength:"长度必须在4-8范围内",
							passwordIsTrue:"密码错误"
						},
						new_pwd:{
							required:"新密码不能为空",
							rangelength:"长度必须在4-8范围内"
						},
						submit_pwd:{
							required:"新密码不能为空",
							rangelength:"长度必须在4-8范围内",
							equalTo:"两次输入不一致"
						}
					}
				});
			});
	 	</script>
	</body>
</html>
