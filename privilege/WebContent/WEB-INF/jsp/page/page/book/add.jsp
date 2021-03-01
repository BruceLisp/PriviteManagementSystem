<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
		您现在所在的位置：图书管理 &rarr; <span>添加图书</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加图书</div>
			<form id="form" action="${pageContext.request.contextPath }/bookAction.action" method="post">
				<input type="hidden" name="method" value="add" />
				<div class="add-page-form">
					<div class="add-page-input">
						<span>图书名称</span>
						<input type="text" name="name" value="" />
					</div>
					<div class="add-page-input">
						<span>作者</span>
						<input type="text" name="author" value=""/>
					</div>
					<div class="add-page-input">
						<span>出版社</span>
						<input type="text" name="productor" value=""/>
					</div>
					<div class="add-page-input">
						<span>类别</span>
							<input type="hidden" id="type" name="type" value="1001"/>
							<select name="selectType" id="selectType">
								<c:forEach items="${varities }" var="varity">
									<option value="${varity.id }"
										<c:if test="${varity.id==book.type }"> selected="selected" </c:if>
									> ${varity.name} </option>
								</c:forEach>
							</select>
					</div>
					<div class="add-page-input">
						<span>价格</span>
						<input type="text" name="price" value=""/>
					</div>
					<div class="add-page-input">
						<span>数量</span>
						<input type="text" name="num" value=""/>
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
					author:{
						required:true
					},
					productor:{
						required:true
					},
					status:{
						required:false
					},
					price:{
						required:true
					},
					num:{
						required:true
					}
					
				},
				messages:{
					name:{
						required:"角色名称不能为空"
					},
					author:{
						required:"作者名字不能为空"
					},
					productor:{
						required:"出版社不能为空"
					},
					status:{
						required:"请选择一个类别"
					},
					price:{
						required:"价格不能为空"
					},
					num:{
						required:"数量不能为空"
					}
				}
			});
		});

		$(document).ready(function(){
			   $("#selectType").change(function(){
			       var selected=$(this).children('option:selected').val();
			       $("#type").attr("value",selected);
			   });
		}); 
	</script>
</html>
