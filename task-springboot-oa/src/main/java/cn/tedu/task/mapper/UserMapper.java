package cn.tedu.task.mapper;

import java.util.List;
import java.util.Map;

import cn.tedu.task.pojo.User;

public interface UserMapper {

	List<User> showUsers(Map<String, String> map);

	
}
