package cn.tedu.popedom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.common.util.MD5Util;
import cn.tedu.common.util.UUIDUtil;
import cn.tedu.popedom.mapper.UserMapper;
import cn.tedu.popedom.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public int  registUser(User user){
		//userName，password（加密），roleId，fatherId
		user.setUserId(MD5Util.md5(UUIDUtil.getUUID()));
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		int result = userMapper.registUser(user);
		return result;
	}
	//通过major名查userId
	public String getMajorId(String major) {
		return userMapper.getUserId2(major);
	}
	//通过角色名查询角色的Id（roleId）
	public String getRoleId(String roleName) {
		return userMapper.getRoleId2(roleName)+"";
	}
	
	public List<String> getUsers(String roleName) {
		Integer fatherRole = userMapper.getFatherrole(roleName);
		return userMapper.getUserName(fatherRole);
	}
}
