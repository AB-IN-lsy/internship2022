package com.mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;
import com.beans.UserInfo;

public interface UserMapper {
	
	@Select("select * from userInfo where id=#{id}")
	UserInfo getUserById(int id);
	
	@Select("select * from userInfo")
	List<UserInfo> getAll();
	
	@Insert("insert into userInfo (userName,password,note) values (#{userName} , #{password}, #{note} )")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	int addUser(UserInfo user);
	
	@Update("update userInfo set userName=#{userName} ,password= #{password}, note =#{note} where id =#{id}")
	int updateUser(UserInfo user);
	
	@Delete("delete from userInfo where id =#{id}")
	int deleteUser(int id);
	
	@Select("select * from userInfo where userName=#{userName} and password=#{password}")
	UserInfo login(@Param("userName") String userName, @Param("password") String password);
	
	//���������ϲ�ѯ
	List<UserInfo> selectList(UserInfo user);
	
	//����id�б���в�ѯ
	List<UserInfo> getUserListByIds( List<Integer> idList); 
	
	//����sql���в�ѯ
	List<UserInfo> getXXXX(String sql);
	
	//��������ѯ�û�����
	int getUserCount(UserInfo user);
}
