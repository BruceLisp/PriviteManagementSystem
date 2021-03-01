<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>自定义弹框</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<style type="text/css">
	.confirm,.cancel{
		font-family: "iconfont" !important;
	  	font-size: 14px;
	  	font-style: normal;
	  	-webkit-font-smoothing: antialiased;
	  	-moz-osx-font-smoothing: grayscale;
	  	float:left; 
	  	margin-left:10px;
	  	cursor:pointer;
	  	color:black;
	  	background: rgba(99, 184, 237,0.5);
	  	border:1px solid rgba(99, 184, 237);
	  	padding: 0 7px;
	}
	.show{
		width:300px;
		height:220px;
		border:solid 1px black;
		opacity:1;	
		margin:100px auto;
		box-shadow:5px 5px 2px #888888;
	}
		
	.hidden{
		visibility:hidden;
	}

</style>
<script type="text/javascript">
	
</script>

</head>
<body>
	<div onclick="trigger()">点击我</div>
	
	<div id="alert" class="show">
		<div style="width:300px;height:100px;text-align:center" >
			<h4>请选择你要借取的时间</h4>
			<input id="period" type="text" style="text-align:center" value="30"/>&nbsp;天
 		</div>
 		<div style="width:300px; height:30px;">
 			<div onclick="option(this.id)" id="3" class="confirm" style="float:left;margin-left:30px">3天</div>
 			<div onclick="option(this.id)" id="5" class="confirm" style="float:left;margin-left:10px">5天</div>
 			<div onclick="option(this.id)" id="7" class="confirm" style="float:left;margin-left:10px">7天</div>
 			<div onclick="option(this.id)" id="15" class="confirm" style="float:left;margin-left:10px">15天</div>
 			<div onclick="option(this.id)" id="30" class="confirm" style="float:left;margin-left:10px">30天</div>
 		</div>
 		<div style="width:300px; height:30px; margin-top:20px;">
 			<div class="confirm" onclick="confirm()" style="float:left;margin-left:70px">确任</div>
			<div class="cancel" onclick="cancle()" style="float:right;margin-right:70px">取消</div>
 		</div>
	</div>

</body>
</html>