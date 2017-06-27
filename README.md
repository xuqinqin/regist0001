# regist0001
# ajax001
<h3>前后端交互</h3>
<p>
开发环境：MyEclipse，tomcat7.0,mybatis，mysql，navicat
<ul>
<li>
前端页面在WebRoot下regist.jsp文件中
</li>
<li>
在regist.jsp中创建验证码的img 标签src=/checkcode通过web.xml文件找到src下CheckcodeServlet是java中AWT编程，绘制验证码，返回到前台.这里需要注意，
返回的图片是字节输出流
</li>
<li>
通过regist.jsp中的onblur写对相应的失焦函数，这里用了ajax的get和post两种方法来实现与后台的交互，通过mybatis访问数据库，实现往数据库中插入用户,在创建用户时，查询数据库
看是否数据库中有该用户。其中Check类中写的是前端页面中输入的用户名，密码，确定密码，
还有验证码检验函数，前台通过ajax以post和get方法(在xml文件通过url地址找到对应的Servlet)传递到后台ActionServlet看是否满足条件，在不刷新整个页面的情况下，来提示注册信息的情况。
</li>
<li>
为什么要单独创建一个Check类？其实Check中的代码完全可以直接写到Servlet文件中，但是，为了代码的健壮性，在提交表单的时候，还在核对一下，所以前台信息是否
满足条件，怕有时候，后台的信息还没有完全提交给前台，就提交表单，因为Check里面的内容会多次用到，所以单独写了一个类为了多次调用。
</li>
</ul>
</p>
