
package org.fkit.domain;

import java.io.Serializable;

/**
 * 瀹樻柟缃戠珯
 * www.fkjava.org
 * www.facejava.org
 * 瀛︿範浜ゆ祦璁哄潧
 * www.crazyit.org
 *
 * @author 鑲栨枃鍚�
 * 
 * CREATE TABLE `tb_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(18) DEFAULT NULL,
  `sex` CHAR(2) DEFAULT NULL,
  `age` INT(11) DEFAULT NULL,
  PRIMARY KEY  (`id`)
)
 */
public class User implements Serializable{
	
	private Integer id;
	private String username;
	private String password2;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User( String username, String password2) {
		super();
		this.username=username;
		this.password2=password2;
	}

	public Integer getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password2=" +password2+"]";
	}
	
	

}
