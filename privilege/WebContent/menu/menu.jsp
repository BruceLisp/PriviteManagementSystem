<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title>
<link href="${pageContext.request.contextPath }/css/admin/right.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/admin/menu.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/z-tree/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/z-tree/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/z-tree/jquery.ztree.all-3.5.min.js"></script>
</head>
<body>
	<div id="right_font">
		<div></div>
		您现在所在的位置：菜单管理 &rarr; <span>菜单管理</span>
	</div>

	<!-- 菜单管理部分 -->
	<div>
		<ul id="menu" class="ztree"></ul>
	</div>


</body>
<SCRIPT type="text/javascript">
			//初始化数据
			var setting = {
				view: {
					addDiyDom: addDiyDom
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
			function addDiyDom(treeId, treeNode) {
				console.log(treeNode);
				
			};
			//插件数据
			var zNodes =[
				{ id:1, pId:0, name:"系统管理",checked:true},
				{ id:2, pId:0, name:"数据字典管理"},
				{ id:3, pId:0, name:"个人信息"},
				{ id:4, pId:0, name:"首页内容管理"},
				{ id:5, pId:0, name:"商品管理"},
				{ id:6, pId:0, name:"订单管理"},
				{ id:7, pId:1, name:"用户管理"},
				{ id:12, pId:7, name:"用户查询"},
				{ id:13, pId:7, name:"用户添加"},
				{ id:8, pId:1, name:"角色管理"},
				{ id:9, pId:2, name:"数据字典管理"},
				{ id:10, pId:3, name:"个人信息查看"},
				{ id:11, pId:3, name:"修改密码"}
			];
			//加载树形菜单
			$(document).ready(function(){
				$.fn.zTree.init($("#menu"), setting, zNodes);
			}); 

			
			
	</SCRIPT>
</html>
