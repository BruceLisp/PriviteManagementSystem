<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/admin/person.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script  src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：个人信息 &rarr; <span>查看个人信息</span>
	</div>
		<div id="show-mes">
			<div class="left">
				<div class="item">
					<div class="tit">用户名</div>
					<div class="content">${loginUser.name }</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">岗位</div>
					<div class="content">
						<c:forEach items="${loginUser.roles}" var="role">
							${role.name }&nbsp;&nbsp;
						</c:forEach>
					</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">手机号</div>
					<div class="content">${loginUser.phone}</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">头像</div>
					<div class="content">
						<input type="hidden" name="url" value="" />
						<button id="upload">上传</button>
					</div>
					<div style="clear:both;"></div>
				</div>
			</div>
			<div class="right">
				<img src="${loginUser.pic}"  />
			</div>
			<div style="clear:both;"></div>
			<div class="add-page-btn" style="margin:20px auto 10px;">
				<a class="save" href="javascript:save();">保存</a>
				<a class="back" href="javascript:back();">返回</a>
				<script type="text/javascript">
					function save(){
						var url = $("input[name='url']").val();
						location.href="${pageContext.request.contextPath}/personAction.action?method=personMessageSave&url="+url
					}
					function back(){
						history.back();
					}
				</script>
			</div>
		</div>
		
	</body>
	
</html>
