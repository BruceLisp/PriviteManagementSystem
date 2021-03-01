<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	权限不足 
	<span id="span1">3</span>S后返回上一页面
	<script>
		var intervalId= setInterval(go,1000);
		function go(){
			var html = document.getElementById("span1").innerHTML;
			html--;
			if(html<1){
				clearInterval(intervalId);
				history.back();
			} else {
				document.getElementById("span1").innerHTML = html;
			}
			
		}
	</script>
</body>
</html>
