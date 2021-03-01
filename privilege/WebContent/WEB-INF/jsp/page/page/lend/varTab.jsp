<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<frameset rows="*" cols="150,*" frameborder="no" border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath }/bookTabAction.action?method=varityLUI" name="varity" noresize="noresize" />
  <frame src="${pageContext.request.contextPath }/bookTabAction.action?method=tableLUI" name="table" noresize="noresize" />
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>