package com.dao.impl;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.beans.UserInfo;

@Repository 
public class UserDaoImpl {
	private JdbcTemplate t;
	
	@Resource  //让spring在运行的时候帮我们把 dataSource注起来
	public void SetDataSoruce(DataSource dataSource) {
		t=new JdbcTemplate(dataSource);
	}
	
	//添加用户
	public int addUser(UserInfo user) {
		String sql="insert into userInfo (userName,password,note) values (?,?,?) ";
		return t.update(sql, user.getUserName(),user.getPassword(),user.getNote());
	}
	
	public void replace() {
		UserInfo user=new UserInfo();
		user.setUserName("小白吃");
		user.setPassword("222555000");
		user.setNote("米国总统");
		
		addUser(user);
		
		int a=9/3; 
		
		deleteUserById(2);
	}
	
	public int deleteUserById(int id){
		//杀人
		int result= t.update("delete from userInfo where id =? ",id);
		return result;
	}
	
	//修改用户
	public int updateUser(UserInfo user) {
		String sql="update userInfo set userName=?, password=?,note=? where id =?" ;
		Object [] params= {
			user.getUserName(),
			user.getPassword(),
			user.getNote(),
			user.getId()
		};
		return t.update(sql, params);
	}

	public UserInfo getUserById(int id) {
		String sql="select * from userInfo where id=?";
		return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
	}
}
