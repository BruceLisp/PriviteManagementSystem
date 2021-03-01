<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</head>
<body>
	<div id="left">
		<div id="left_menu"></div>
		<div id="left_tree">
			<div id="tree_text">
				<ul id="globalNav">
					<!--  假设该用户拥有所有权限动态显示菜单 -->
					<c:forEach items="${applicationScope.topPrivileges }" var="topPrivilege">
						<c:if test="${loginUser.hasPrivilegeByName(topPrivilege.name)}">
							<li>
								<a>${topPrivilege.name }</a>
								<ul>
									<c:forEach items="${topPrivilege.children}" var="privilege">
										<c:if test="${loginUser.hasPrivilegeByName(privilege.name)}">
											<li>
												<a class="a" href="${pageContext.request.contextPath}${privilege.url}&id=${loginUser.id }" target="right">${privilege.name }</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
					<!-- <li>
						<a>系统管理管理</a>
						<ul>
							<li>
								<a class="a" href="../page/user/table.jsp" target="right">用户管理</a>
							</li>
							<li>
								<a class="a" href="../page/role/table.jsp" target="right">角色管理</a>
							</li>
							
						</ul>
					</li> -->
					
				</ul>
			</div>
		</div>
     </div>
</body>
<script type="text/javascript">
$(function(){
	initHeight();
	$("#globalNav").children().each(function(index){
		var item = $(this);
		var tag_a = item.children().eq(0);
		tag_a.attr("href", "#this");
		var tag_ul = item.children().eq(1);
		if(index == 0) {
			tag_ul.show();
		}
		tag_a.click(function(){
			tag_ul.toggle();
		});
	});
	
	$(".a").click(function(){
		$(".a").css({"color":"black", "font-weight":"normal"});
		$(this).css({"color":"red", "font-weight":"bold"});
	});
})
window.onresize=function() {
	initHeight();
}
function initHeight(){
	$("#left_tree").css("height", document.documentElement.clientHeight-80);
	$("#tree_text").css("height", document.documentElement.clientHeight-80);
}
</script>
</html>


