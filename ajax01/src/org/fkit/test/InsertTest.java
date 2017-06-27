package org.fkit.test;

import org.apache.ibatis.session.SqlSession;
import org.fkit.domain.User;
import org.fkit.factory.FKSqlSessionFactory;

public class InsertTest {

	public static void main(String[] args) throws Exception {
		SqlSession session = FKSqlSessionFactory.getSqlSession();
		
		User user = new User("小花","xuqin1993");
	
		session.insert("org.fkit.mapper.UserMapper.saveUser", user);
		
		User user3 = new User("豆豆","xiaoqinqin");
		
		session.insert("org.fkit.mapper.UserMapper.saveUser", user3);
		
		User user2 = session.selectOne("org.fkit.mapper.UserMapper.selectUser",1);
		user2.setUsername("tom");
		user2.setPassword2("hahahah");
		session.update("org.fkit.mapper.UserMapper.modifyUser", user2);
		
		session.delete("org.fkit.mapper.UserMapper.removeUser", 3);
		session.commit();
		
		session.close();
	}

}
