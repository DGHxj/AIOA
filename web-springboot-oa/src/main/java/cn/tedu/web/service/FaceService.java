package cn.tedu.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.CollectionType;

import cn.tedu.common.service.HttpClientService;
import cn.tedu.common.util.ObjectUtil;
import cn.tedu.web.pojo.User;

@Service
public class FaceService {

	@Autowired
	private HttpClientService client;
	
	
	public String savePic(String image,String path,String userId) throws Exception {
		String url="http://sign.oa.com/face/url";
		image=image+","+path+","+userId;

		String doPostJson = client.doPostJson(url, image);
		return doPostJson;
	}


	public int regist(User user) throws Exception {
		String url="http://sign.oa.com/face/regist";
		String userStr=ObjectUtil.mapper.writeValueAsString(user);
		String doPostJson = client.doPostJson(url, userStr);
		return Integer.parseInt(doPostJson);
	}


	public boolean book(String image,String userId) throws Exception {
		String url="http://sign.oa.com/face/book";
		String imageStr=image.split(",")[1];
		String info=imageStr+","+userId;
		String doPostJson = client.doPostJson(url, info);
		int s = Integer.parseInt(doPostJson);
		if(s==1){
			return true;
		}
		return false;
	}


	public List<String> getFaceData(String userId) throws Exception {
		String url="http://sign.oa.com/face/getData";
		String doPostJson = client.doPostJson(url, userId);
		JsonNode node=ObjectUtil.mapper.readTree(doPostJson);
		CollectionType constructCollectionType = ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, String.class);
		List<String> list=ObjectUtil.mapper.readValue(node.traverse(), constructCollectionType);
		return list;
	}

}
