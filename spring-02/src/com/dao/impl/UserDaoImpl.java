package com.dao.impl;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.beans.UserInfo;

@Repository
public class UserDaoImpl {
	private JdbcTemplate t;   //�����,��Spring�ṩ��,һ�����������ݿ��������
	
	@Resource  //��spring�����е�ʱ������ǰ� dataSourceע����
	public void SetDataSoruce(DataSource dataSource) {
		t=new JdbcTemplate(dataSource);
	}
	
	//����û�
	public int addUser(UserInfo user) {
		String sql="insert into userInfo (userName,password,note) values (?,?,?) ";
		return t.update(sql, user.getUserName(),user.getPassword(),user.getNote());
	}
	
	//ɾ���û�
	public int deleteUserById(int id){
		return t.update("delete from userInfo where id =? ",id);
	}
	
	//�޸��û�
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
	
	//����id��ѯ�û�
	public UserInfo getUserById(int id) {
		String sql="select * from userInfo where id=?";
		return t.queryForObject(sql,  new BeanPropertyRowMapper<UserInfo>(UserInfo.class),id);	
	}
}