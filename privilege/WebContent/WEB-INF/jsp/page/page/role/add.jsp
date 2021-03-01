<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色添加页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：角色信息 &rarr; <span>添加角色</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加角色</div>
			<form id="form" action="${pageContext.request.contextPath }/roleAction.action" method="post">
				<input type="hidden" name="method" value="add" />
				<div class="add-page-form">
					<div class="add-page-input">
						<span>角色名称</span>
						<input type="text" name="name" value="" />
					</div>
					
					<div class="add-page-input">
						<span>角色说明</span>
						<textarea name="description" value=""></textarea>
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
	
	</body>
	<script type="text/javascript">
		$(function(){
			$("#form").validate({
				rules:{
					name:{
						required:true
					},
					description:{
						required:true
					}
					
				},
				messages:{
					name:{
						required:"角色名称不能为空"
					},
					description:{
						required:"角色的描述信息不能为空"
					}
				}
			});
		});
	</script>
</html>
