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
	<span>图书分类管理</span>
	<!-- 菜单管理部分 -->
	<div>
		<ul id="varity" class="ztree"></ul>
	</div>
</body>
<script type="text/javascript">
			//初始化数据
			var setting = {
				view:{
	                addHoverDom:addHoverDom,
	                removeHoverDom:removeHoverDom,
	                selectedMulti:false
	            },
				data: {
					simpleData: {
						enable: true
					}
				},
				edit: {
					enable: true,
	                editNameSelectAll:true,
	                removeTitle:'删除',
	                renameTitle:'重命名'
				},
			 	callback:{
	                beforeRemove:beforeRemove,//点击删除时触发，用来提示用户是否确定删除（可以根据返回值 true|false 确定是否可以删除）
	                beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
	                beforeRename:beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求(也是根据返回值 true|false 确定是否可以编辑完成)
	                onRemove:onRemove,//(beforeRemove返回true之后可以进行onRemove)删除节点后触发，用户后台操作
	                onRename:onRename,//编辑后触发，用于操作后台
	                beforeDrag:beforeDrag,//用户禁止拖动节点
	                onClick:clickNode//点击节点触发的事件
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
			
			function beforeRemove(e,treeId,treeNode){
				return confirm("你确定要删除吗？");
			}
		    function onRemove(e,treeId,treeNode){
		        if(treeNode.isParent){
		            var childNodes = zTree.removeChildNodes(treeNode);
		            var paramsArray = new Array();
		            for(var i = 0; i < childNodes.length; i++){
		                paramsArray.push(childNodes[i].id);
		            }
		            alert("删除父节点的id为："+treeNode.id+"\r\n他的孩子节点有："+paramsArray.join(","));
		            return;
		        }
		        alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id);
		    }
		    function beforeEditName(treeId,treeNode){
		        /* if(treeNode.isParent){
		            alert("不准编辑非叶子节点！");
		            return false;
		        } */
		        return true;
		    }
		    function beforeRename(treeId,treeNode,newName,isCancel){
		        if(newName.length < 3){
		            alert("名称不能少于3个字符！");
		            return false;
		        }
		        return true;
		    }
		    function onRename(e,treeId,treeNode,isCancel){
		        alert("修改节点的id为："+treeNode.id+"\n修改后的名称为："+treeNode.name);
		    }
		    function clickNode(e,treeId,treeNode){
		        if(treeNode.id == 11){
		            location.href=treeNode.url;
		        }else{
		            alert("节点名称："+treeNode.name+"\n节点id："+treeNode.id);
		        }
		    }
		    function beforeDrag(treeId,treeNodes){
		        return false;
		    }
		    var newCount = 1;
		    function addHoverDom(treeId,treeNode){
		    	var aObj = $("#" + treeNode.tId + "_a");
		    	if ($("#diyBtn_"+treeNode.id).length>0) return;
		    	var editStr = "<span class='button add' style='float:right' id='diyBtn_space_" +treeNode.id+ "' >"
		    		+ "<button type='button' style='visibility:hidden' class='diyBtn1' id='diyBtn_" + treeNode.id
		    		+ "' title='添加' onfocus='this.blur();'></button> </span>";
		    	aObj.append(editStr);
		    	var btn = $("#diyBtn_"+treeNode.id);
		    	if (btn) btn.bind("click", function(){
		    	//<!-- 向服务器请求添加类别 -->	
		    		var node = "<span class='button add' style='float:right' id='diyBtn_space_" +treeNode.id+ "' >"
		    		+ "<button type='button' style='visibility:hidden' class='diyBtn1' id='diyBtn_" + treeNode.id
		    		+ "' title='添加' onfocus='this.blur();'></button> </span>";
		    		aObj.append(node);
		    	});
		    }
		    function removeHoverDom(treeId,treeNode){
		        $("#diyBtn_"+treeNode.id).unbind().remove();
		    	$("#diyBtn_space_" +treeNode.id).unbind().remove();
		    }
	</script>
</html>
