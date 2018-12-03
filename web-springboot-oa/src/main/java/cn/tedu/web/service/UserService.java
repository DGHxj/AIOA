package cn.tedu.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.common.service.HttpClientService;
import cn.tedu.common.util.ObjectUtil;
import cn.tedu.web.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private HttpClientService client;
	
	public User login(String userName,String userPassword) throws Exception{
		String url = "http://sign.oa.com/sign/login?userName="+userName+"&userPassword="+userPassword;
		String jsonData = client.doGet(url);
		User user = ObjectUtil.mapper.readValue(jsonData, User.class);
		return user;
	}
	
}
