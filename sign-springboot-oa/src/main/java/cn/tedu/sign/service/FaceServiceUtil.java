package cn.tedu.sign.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.fasterxml.jackson.databind.JsonNode;

import cn.tedu.common.util.ObjectUtil;

@Service
public class FaceServiceUtil {


	@Autowired
	private AipFace client;

	/**
	 * 人脸对比,返回是否是员工
	 * 
	 * @param tokens
	 * @param image
	 * @throws Exception
	 */
	public boolean isEmp(String token1, String image) throws Exception {
		String token = getFaceToken(image);
		if(token==null) return false;
		token=token.substring(1, token.length()-1);
		MatchRequest m1 = new MatchRequest(token, "FACE_TOKEN");
		MatchRequest m2 = new MatchRequest(token1, "FACE_TOKEN");
		String score = getScore(m1, m2);
		if(score==null||score=="null") return false;
		int score2 = (int) Double.parseDouble(score);
		if (score2 > 80) {
			return true;
		}
		return false;

	}
	

	/**
	 * 获取图片唯一token
	 * 
	 * @param image
	 *            图片的base64编码
	 * @param client
	 *            百度接口端
	 * @return
	 * @throws Exception
	 */
	public String getFaceToken(String image) throws Exception {
		JSONObject detect = client.detect(image, "BASE64", new HashMap<String, String>());
		JsonNode node = ObjectUtil.mapper.readTree(detect.get("result").toString());
		String result = node.get("face_list").toString();
		JsonNode node2 = ObjectUtil.mapper.readTree(result.substring(1, result.length()));
		if(node2==null) return null;
		String token = String.valueOf(node2.get("face_token"));
		return token;
	}
	/**
	 * 人脸注册
	 */
	public String getFaceTokenAR(String image,String userId) throws Exception {
		JSONObject detect = client.addUser(image, "BASE64", "TestGroup", userId, new HashMap<String, String>());
		JsonNode node = ObjectUtil.mapper.readTree(detect.get("result").toString());
		if(node==null) return null;
		String token = String.valueOf(node.get("face_token"));
		return token;
	}

	/**
	 * 获取对比得分
	 * 
	 * @param m1
	 * @param m2
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public String getScore(MatchRequest m1, MatchRequest m2) throws Exception {
		List<MatchRequest> list = new ArrayList<MatchRequest>();
		list.add(m1);
		list.add(m2);
		JSONObject res = client.match(list);
		JsonNode node = ObjectUtil.mapper.readTree(res.get("result").toString());
		return String.valueOf(node.get("score"));

	}
}
