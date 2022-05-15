package com.dao.impl;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.beans.UserInfo;

@Repository
public class UserDaoImpl {
	private JdbcTemplate t;   //这个类,是Spring提供的,一个传用于数据库操作的类
	
	@Resource  //让spring在运行的时候帮我们把 dataSource注起来
	public void SetDataSoruce(DataSource dataSource) {
		t=new JdbcTemplate(dataSource);
	}
	
	//添加用户
	public int addUser(UserInfo user) {
		String sql="insert into userInfo (userName,password,note) values (?,?,?) ";
		return t.update(sql, user.getUserName(),user.getPassword(),user.getNote());
	}
	
	//删除用户
	public int deleteUserById(int id){
		return t.update("delete from userInfo where id =? ",id);
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
	
	//根据id查询用户
	public UserInfo getUserById(int id) {
		String sql="select * from userInfo where id=?";
		return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
	}
}