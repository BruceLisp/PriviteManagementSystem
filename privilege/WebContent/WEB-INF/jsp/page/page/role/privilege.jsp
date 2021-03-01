<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>权限管理页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/admin/privilege.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/z-tree/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery.ztree.all-3.5.min.js"></script>
	</head>
	<body>
		<div id="right_font">
			<div></div> 
			您现在所在的位置：【${role.name }】权限信息 &rarr; <span>【${role.name }】权限管理</span>
		</div>
		<div id="privilege">
			
			<ul id="treeDemo" class="ztree"></ul>
			<div class="add-page-btn" style="margin:100px auto 0;">
				<a class="save" href="javascript:save();">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function save(){
						//获得选中的权限节点
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var nodes = treeObj.getCheckedNodes(true);
						//将权限节点的id拼接到一个字符串中
						var ids = [];
						for(var i=0;i<nodes.length;i++){
							ids.push(nodes[i].id);
						}
						//将权限id数组按照逗号分隔成字符串
						var privilegeIds = ids.join(",");
						location.href="${pageContext.request.contextPath }/roleAction.action?method=privilegeSave&roleId="+${role.id}+"&privilegeIds="+privilegeIds;
					}
					function back(){
						history.back();
					}
				</script>
			</div>
		</div>
	
	</body>
	<SCRIPT type="text/javascript">
		//初始化数据
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		/* //插件数据
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
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}); */
		//动态加载权限数据
		$(function(){
			var roleId = ${role.id};
			//发送ajax请求获得权限数据
			$.ajax({
				//请求的路径
				url:"${pageContext.request.contextPath}/roleAction.action",
				//提交方式
				type:"post",
				//提交的参数
				data:{"method":"privilegeShow","id":roleId},
				//响应数据的格式
				dataType:"json",
				//响应成功返回的数据
				success:function(data){
					$.fn.zTree.init($("#treeDemo"), setting, data);
				}
			});
		});
		
</SCRIPT>
</html>
