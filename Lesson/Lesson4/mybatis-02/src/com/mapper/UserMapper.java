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
	
	//多条件复合查询
	List<UserInfo> selectList(UserInfo user);
	
	//根据id列表进行查询
	List<UserInfo> getUserListByIds( List<Integer> idList); 
	
	//传入sql进行查询
	List<UserInfo> getXXXX(String sql);
	
	//多条件查询用户总数
	int getUserCount(UserInfo user);
}
