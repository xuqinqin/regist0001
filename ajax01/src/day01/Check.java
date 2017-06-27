package day01;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.fkit.domain.User;
import org.fkit.factory.FKSqlSessionFactory;

public class Check {
	public String usn(String username){
		SqlSession session3 = FKSqlSessionFactory.getSqlSession();
		User user2 = session3.selectOne("org.fkit.mapper.UserMapper.selectUserByUsername",username);
		if(user2!=null){
			return "该用户名被占用";
		}else if(username.equals("")){
			return "请输入用户名";
		}else{
			return "该用户名可以使用";
		}
	}
	public String psw1(String password1,String username){
		int length1=password1.length();
		if(length1==0||password1==null){
			return "密码不能为空";
		}
		if(length1>=1&length1<7){
			return "密码不能少于七位";
		}else{
			boolean flag1=false;	//是否有数字
			boolean flag2=false;	//是否有字母
			for(int i=0;i<length1;i++){
				if(String.valueOf(password1.charAt(i)).matches("[0-9]")){
					flag1=true;
				}
				if(String.valueOf(password1.charAt(i)).matches("[a-zA-Z]")){
					flag2=true;
				}
			}
			if(!flag1&!flag2)
				return "密码必须由数字和字母组成";
			if(flag1&!flag2)
				return "密码中必须含有字母";
			if(!flag1&flag2)
				return "密码中必须含有数字";
			if(flag1&flag2){
				if(password1.equals(username))
					return "用户名和密码不能相同";
			}
			return "密码创建成功";
		}
	}
	public String psw2(String password1,String password2){
		if(password1.equals(password2)){
			return "确认密码输入正确";
		}else  {
			return "确认密码错误，请重输";
		}
	}
	public String num(String number1,String number2){
		if(number1.equalsIgnoreCase(number2)){
			return "验证码正确";
		}else if(number1.equals("")){
			return "请输入验证码";
		}else{
			return "验证码错误";
		}
	}
	 public static String getMD5(String str) {
	        try {
	            // 生成一个MD5加密计算摘要
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // 计算md5函数
	            md.update(str.getBytes());
	          
	            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	            return new BigInteger(1, md.digest()).toString(16);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return str;
	        }
	    }
}
