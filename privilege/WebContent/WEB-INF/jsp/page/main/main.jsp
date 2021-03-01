<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理</title>
</head>
<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath }/mainAction.action?method=topUI" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
	<frameset rows="*" cols="188,*" framespacing="0" frameborder="no" border="0">
		<frame src="${pageContext.request.contextPath }/mainAction.action?method=leftUI" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame"/>
		<frameset rows="35,*" cols="*">
		  <frame src="${pageContext.request.contextPath }/mainAction.action?method=rightTopUI" name="mainFrame" id="mainFrame" scrolling="no"/>
		  <frame src="${pageContext.request.contextPath }/mainAction.action?method=rightUI" name="right"/>
		</frameset>
	 </frameset>
</frameset>
	
<noframes>
	<body>
	</body>
</noframes>
</html>
