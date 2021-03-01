<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<link href="${pageContext.request.contextPath }/css/admin/right.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/admin/menu.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/z-tree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
	<span>图书分类</span>
	<!-- 菜单管理部分 -->
	<a id="jump" href="#" target="table" style="visibility:hidden" ><span id="tri">hidden</span></a>
	<div>
		<ul id="varity" class="ztree"></ul>
	</div>
</body>
<script type="text/javascript">
			function jumpToNode(event, treeId, treeNode){
				var vid = treeNode.id;
				$("#jump").attr("href","${pageContext.request.contextPath }/bookTabAction.action?method=jumpToVar&vid="+vid);
				$("#tri").trigger("click");
			}
			//初始化数据
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick:jumpToNode
				}			
			};
			$(function(){
				//发送ajax请求获得权限数据
				$.ajax({
					//请求的路径
					url:"${pageContext.request.contextPath}/bookAction.action",
					//提交方式
					type:"post",
					//提交的参数
					data:{"method":"varityShow"},
					//响应数据的格式
					dataType:"json",
					//响应成功返回的数据
					success:function(data){
						$.fn.zTree.init($("#varity"), setting, data);
					}
				});
			});
	</script>
</html>
