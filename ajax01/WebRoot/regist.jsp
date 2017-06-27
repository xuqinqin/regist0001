<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- <base href="<%=basePath%>"> -->
    <!-- <base href="http://localhost:8888/ajax01/"> -->
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style>
	legend{
    font-size: 33px;
    font-weight: bold;
    color: #f3c;
    font-family: "Times New Roman",Georgia,Serif;
  
}
body{
    margin:0px;
    padding: 0px;
/*background-image:url(2.jpg);
 filter:alpha(Opacity=60);
    -moz-opacity:0.6;
    opacity: 0.6;*/
   
}
.img1{
   
    z-index: 1;
position: absolute;//注意解决了背景透明度覆盖了表单的问题
 filter:alpha(Opacity=90);
    -moz-opacity:0.9;
    opacity: 0.9;
}

.big{

    background: #fff;
    width:26%;
    height:350px;
    margin-top:100px;
    margin-left:35%;
    border:1px dashed #fff;
  position: absolute;
   z-index: 3;

}
form label{
   padding-left:1px;
    float:left;
    text-align:right;
    width:85px;

}
.tips{
color:red;
font-size:9px;
}

.first{
   margin-top: 5px;
    position: relative;
}
.tijiao{
    margin-left: 150px;
}
a{
    text-decoration: none;
    color: #000;
    font-size: 15px;

}
.text1{

padding-bottom:3px;}
.second{
    margin-left: 70px;
}
		
	</style>
	
	<script type="text/javascript" src="js/my.js"></script> 
	<script type="text/javascript">
		function check_username(){
			//第一步：获取ajax对象
			var xhr=getXhr();
			//alert(xhr);
			//第二步：使用ajax对象发送请求
			var uri="check_username.do?username="+$F("username");
			xhr.open("get",encodeURI(uri),true);//encodeURI(uri),为了解决ie解析request兼容性问题，为了欺骗ie,encodeURI这个函数，为了解决uri里面中文以utf-8来解析
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){//服务器返回了正确的结果
						//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("username_msg").innerHTML=text;//当从服务器读到返回值，执行这个，为最终显示到页面上
					}
					else{
					//服务器运行出错
						$("username_msg").innerHTML="验证出错....";
					}
					
				}
			};
			$("username_msg").innerHTML="正在验证....";//先执行这个
			xhr.send(null);
		}
		function check_number(){
		var xhr=getXhr();
		xhr.open("get","check_number.do?number="+$F("number"),true);
		xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("number_msg").innerHTML=text;
				}
			};
			xhr.send(null);//get方法send里面为null，post里面是参数
		}
		function check_username_post(){
			var xhr=getXhr();
			xhr.open("post","check_username.do",true);
			//添加一个消息头content-type
			xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded");
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){//服务器返回了正确的结果
						//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("username_msg").innerHTML=text;//当从服务器读到返回值，执行这个，为最终显示到页面上
					}
					else{
					//服务器运行出错
						$("username_msg").innerHTML="验证出错....";
					}
				}
			};
				$("username_msg").innerHTML="正在验证....";//先执行这个
				xhr.send("username="+$F("username"));
		}
		function check_number_post(){
			var xhr=getXhr();
		xhr.open("post","check_number.do?",true);
		xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded");
		xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("number_msg").innerHTML=text;
				}
			};
			xhr.send("number="+$F("number"));//get方法send里面为null，post里面是参数
		}
		function check_password1_post(){
				var xhr=getXhr();
		xhr.open("post","check_password1.do?",true);
		xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded");
		xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("password1_msg").innerHTML=text;
				}
			};
			xhr.send("password1="+$F("password1")+"&username="+$F("username"));//get方法send里面为null，post里面是参数
		}
		function check_password2_post(){
				var xhr=getXhr();
		xhr.open("post","check_password2.do?",true);
		xhr.setRequestHeader("content-type",
			"application/x-www-form-urlencoded");
		xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					//只有readyState=4，ajax对象才获得了从服务器的所有数据
					var text=xhr.responseText;
					//alert(text);
					//利用服务器返回的数据更新当前页面
					$("password2_msg").innerHTML=text;
				}
			};
			xhr.send("password1="+$F("password1")+"&password2="+$F("password2"));//get方法send里面为null，post里面是参数
		}
	</script>
  </head>
  
  <body >
     <img class="img1" src="img/2.jpg"/>
  		<form action="regist.do" method="post">
  		
  		 <fieldset class="big">
            <legend >注册</legend>
            <!--用户名：<input name="username" id="username" onblur="check_username();"/>--><!-- get方法传送 -->
            <div class="first">
          
            <label for="head1">用户名：</label>
            <input name="username" id="username" onblur="check_username_post();"/>
            <span class="tips" id="username_msg"></span>
           
            </div>
             <br/>
            <div class="first">
            <label for="head1">真实姓名：</label><input name="name"/>
            <!--验证码：<input name="number" id="number" onblur="check_number();"> --><!-- get方法传送-->
            </div>
            <br/>
          
            <div class="first">
            <label for="head1">密码：</label><input name="password1" id="password1" onblur="check_password1_post();"/><!--post方法传送-->
            <span class="tips" id="password1_msg"></span>
            </div>
            <br/>
            <div class="first">
            <label for="head1">确认密码：</label><input name="password2" id="password2" onblur="check_password2_post();"/><!--post方法传送-->
            <span class="tips" id="password2_msg"></span>
            </div>
            <br/>
              <div class="first">
            <label for="head1">验证码：</label><input name="number" id="number" onblur="check_number_post();"/><!--post方法传送-->
            <span class="tips" id="number_msg"></span>
            </div>
            <br/>
            <div class="second">
            <img src="checkcode" id="img1"/>
            <!-- 也可以写成这样 <img src="checkcode" id="img1" onclick="this.src='checkcode?'+Math.random()"/>实现的是点击图片换一张验证码-->
            <a href="javascript:;" onclick="$('img1').src='checkcode?'+Math.random();"><span class="text1">看不清就点这</span></a>
            <!--  'checkcode?'+Math.random()如果跟上面写一样checkcode浏览器不能访问
            写成这样就是为了伪装，欺骗浏览器，浏览器就会访问，浏览器通过向这个伪装的地址发请求，服务器只解析
            ？前面的 看做地址，？后面看做参数-->
            </div>
            <br/>
            <input type="submit" value="提交" class="tijiao"/>
        </fieldset> 
  
  </body>
</html>
