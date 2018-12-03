package cn.tedu.sign.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.sign.pojo.User;

public interface UserMapper {

	User getUserByUP(@Param("userName") String userName, @Param("userPassword") String userPassword);

	int updateUserImg(User user);

	String getToken(String userId);

	List<String> getData(String userId);

}
