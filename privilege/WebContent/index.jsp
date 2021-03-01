<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<link href="${pageContext.request.contextPath }/css/admin/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
</head>
<body>
    <div id="login">
	     <div id="top">
		      <div id="top_left"><img src="${pageContext.request.contextPath }/imgs/admin/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 <form id="form" action="${pageContext.request.contextPath }/userAction.action">
			 <input type="hidden" name="method" value="login" />
			 <div id="center">
			      <div id="center_left"></div>
				  <div id="center_middle">
				  	   <div id="user">用户名：
				         <input type="text"  autocomplete="off" value="" name="name" />
				       </div>
					   <div id="password">密 码：
					     <input type="password" name="pwd" value="" />
					   </div>
					   <div id="code">验证码：
					     <input type="text" autocomplete="off" name="code" id="cod" onFocus="showCode();" onclick="showCode()" />
					    	<span style="color:red;"> ${error }</span>
					   </div>	   
					   <div id="btn">
							<img title="看不清请刷新"
							 src="${pageContext.request.contextPath }/codeAction.action"
							 onclick="this.src='${pageContext.request.contextPath }/codeAction.action?num='+Math.random();"/>
							<a href="javascript:login();">登录</a>
							<script type="text/javascript">
								function login(){
									$("#form").submit();
								}
							</script>
					   </div>
						  
				  </div>
				  <div id="center_right"></div>		 
			 </div>
		 </form>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">图书综合管理系统 2019 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center">
			  	<a href="forget.html">忘记密码！</a>
			  </div>		 
		 </div>
	</div>
</body>
<script type="text/javascript">
		$(function(){
			$("#form").validate({
				rules:{
					name:{
						required:true
					},
					pwd:{
						required:true,
						rangelength:[4,8]
					},
					code:{
						required:true
					}
				},
				messages:{
					name:{
						required:"用户名不能为空"
					},
					pwd:{
						required:"密码不能为空",
						rangelength:"密码长度必须在4-8范围内"
					},
					code:{
						required:"验证码不能为空"
					}
				}
			});
		});
</script>
</html>