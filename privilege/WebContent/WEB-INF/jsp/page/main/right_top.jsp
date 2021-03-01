<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="right_top">
		<div id="img" onclick="openc()">
			<img src="${pageContext.request.contextPath }/imgs/admin/close.gif" />
		</div>
		<span id="spanco" class="imgtext" onclick="openc()">关闭左栏</span>
		
		<span class="imgtext2"></span>
		<div class="weather">
			<marquee id="weather_marquee" direction="left" scrollamount="3" onmouseover="this.stop()" onmouseout="this.start()">
				欢迎【${loginUser.name }】登录....
			</marquee>			
		</div>
		
		
		<div id="loginout">
			<div id="loginoutimg">
				<img src="${pageContext.request.contextPath }/imgs/admin/loginout.gif" onclick="window.parent.location.href='${pageContext.request.contextPath}/userAction.action?method=exit'" />
				
			</div>
			
		</div>
	</div>
	
</body>
</html>
<script type="text/javascript">
	var menu=window.parent.parent.document.getElementsByTagName("frameset")[1]; 
	var spanco = document.getElementById("spanco");
	var img = document.getElementById("img");
	
	function openc(){
		if (spanco.innerHTML =="关闭左栏"){
			menu.cols="0,*";
			spanco.innerHTML = "打开左栏";
			img.innerHTML = "<img src=\"${pageContext.request.contextPath }/imgs/admin/open.gif\" />";
		}else{
			menu.cols="188,*";
			spanco.innerHTML = "关闭左栏";
			img.innerHTML = "<img src=\"${pageContext.request.contextPath }/imgs/admin/close.gif\" />";
		}
		
	}
</script>

