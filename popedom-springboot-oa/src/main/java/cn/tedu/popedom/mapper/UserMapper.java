package cn.tedu.popedom.mapper;

import java.util.List;

import cn.tedu.popedom.pojo.User;

public interface UserMapper {
	int registUser(User user);

	String getUserId2(String major);

	Integer getRoleId2(String roleName);
	

	Integer getFatherrole(String roleName);

	List<String> getUserName(Integer fatherRole);
}
