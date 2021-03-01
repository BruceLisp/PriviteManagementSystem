<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TOP</title>
<link href="${pageContext.request.contextPath }/css/admin/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</head>
<body>
<div id="top">
	      <div id="logo"></div>
		  <div id="user">【欢迎您】：${loginUser.name} 【IP】：<%=request.getRemoteAddr() %>【今天】：<span id="time"><span></span>
		  	<script type="text/javascript">
		  		$(function(){
		  			$("#time").html(new Date().toLocaleString());
		  		});
		  		window.setInterval(go, 1000);
		  		function go(){
		  			$("#time").html(new Date().toLocaleString());
		  		}
		  	</script>
		  </div>	 
	 </div>
</body>
</html>
