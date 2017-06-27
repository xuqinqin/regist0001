package day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.fkit.domain.User;
import org.fkit.factory.FKSqlSessionFactory;

import day01.*;

public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//只对post请求有效,post请求发生乱码问题解决比较方便，只需要写这句话就好
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter();//字符输出流，输出文本
		String uri=request.getRequestURI();
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		Check check=new Check();
		SqlSession session1 = FKSqlSessionFactory.getSqlSession();
		if(action.equals("/check_username")){//equals()是看内容是否相等
			//模拟一个耗时操作
//			try{
//				Thread.sleep(6000);
//			}catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			//模拟一个系统异常
//			if(1==1){
//				throw new ServletException("some error");
//			}
			String username=request.getParameter("username");
			//System.out.println(username);
			out.println(check.usn(username));
		}
		
		if (action.equals("/check_password1")) {
			
			String password1=request.getParameter("password1");
			String username=request.getParameter("username");
			out.println(check.psw1(password1,username));
		}
		if (action.equals("/check_password2")) {
			String password1=request.getParameter("password1");
			String password2=request.getParameter("password2");
			out.println(check.psw2(password1,password2));
		}
		if (action.equals("/check_number")) {
			String number1=request.getParameter("number");
			HttpSession session=request.getSession();
			String number2=(String)session.getAttribute("number");
			out.println(check.num(number1,number2));
		}
		if (action.equals("/regist")) {//点击提交就会提交表单到服务器
			//加上验证代码，比如，要检查用户名是否正确，验证码是否正确
			//这里加上在服务器这边验证，就是为了代码的健壮性
			//因为上面的提交时js验证的，但是Js一旦不小心被禁止了，会出错
			//错误就不让其提交
			String username=request.getParameter("username");
			boolean usn=check.usn(username).equals("该用户名可以使用");
			String password1=request.getParameter("password1");
			boolean psw1=check.psw1(password1,username).equals("密码创建成功");
			String password2=request.getParameter("password2");
			boolean psw2=check.psw2(password1,password2).equals("确认密码输入正确");
			String number1=request.getParameter("number");
			HttpSession session=request.getSession();
			String number2=(String)session.getAttribute("number");
			boolean num=check.num(number1,number2).equals("验证码正确");
			if(usn&&psw1&&psw2&&num){
				System.out.println("注册成功"+"username="+username+"password2="+password2);
				String md5 = check.getMD5(password2);
				User user = new User(username,md5);
				
				session1.insert("org.fkit.mapper.UserMapper.saveUser", user);
				session1.commit();
				
				session1.close();
				response.sendRedirect("https://www.baidu.com/");
			}else{
				response.sendRedirect("/ajax01/regist.jsp"); 
			}
		}
		 
	}
}
