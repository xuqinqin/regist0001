package day01;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class CheckcodeServlet extends HttpServlet {
	private int WIDTH=80;
	private int HEIGHT=30;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//step1.绘图
			//1,创建一个内存映像对象（类似与一个画板）
		BufferedImage image=
				new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			//2,获得画笔
		Graphics g=image.getGraphics();
			//3,给笔上色
		Random r=new Random();
		g.setColor(new Color(100+r.nextInt(155), 100+r.nextInt(155),
				100+r.nextInt(155)));
			//4.给画布设置一个背景颜色
		g.fillRect(0 , 0, WIDTH, HEIGHT);
			//5，绘图
		//String number=r.nextInt(99999)+"";获得随机的几位数字，但是太简单，需要复杂的验证码
		String number=getNumber(5);
		//将number要绑定到session对象上
		HttpSession session=request.getSession();
		session.setAttribute("number", number);
		g.setColor(new Color(r.nextInt(100), r.nextInt(100),
				r.nextInt(100)));//设置字符串为黑色，因为不设置背景和字一样的颜色显示不出来
		//Font(字体类型，风格，高度)
		g.setFont(new Font(null, Font.BOLD|Font.ITALIC, 18));//18为字体的颜色
		g.drawString(number,8,20);//字体距离背景框左边的距离和背景框的高度
		//加干扰线，为了防止机器识别
		for(int i=0;i<5;i++){//加五条干扰线
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		
			//step2.将图片压缩，然后输出到客户端（浏览器）
		response.setContentType("image/jpeg");//注意：这个返回跟其他的不一样
		OutputStream os=response.getOutputStream();//字节输出流(因为要输出的是图片
		//将原始图片（image）按照指定的压缩格式jpeg进行压缩，然后将压缩之后生成的数据通过os
		//输出到response对象上，服务器会从response对象上取出数据，打包发给浏览器
		javax.imageio.ImageIO.write(image, "jpeg",os);
		 
	}
	//生成一个验证码，要求长度为5，并且随机从A~Z.0~9选取
	private String getNumber(int size) {
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String rs="";
		Random r=new Random();
		for(int i=0;i<size;i++){
			rs+=str.charAt(r.nextInt(str.length()));
		}
		return rs;
	}

}
