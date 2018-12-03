package cn.tedu.sign.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.qos.logback.core.pattern.SpacePadder;
import cn.tedu.common.util.ObjectUtil;
import cn.tedu.common.util.UUIDUtil;
import cn.tedu.sign.mapper.CheckMapper;
import cn.tedu.sign.mapper.UserMapper;
import cn.tedu.sign.pojo.User;
import cn.tedu.sign.service.FaceServiceUtil;

@RestController
@RequestMapping("/face")
public class FaceController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CheckMapper checkMapper;
	@Autowired
	private FaceServiceUtil faceUtil;

	/**
	 * 保存人脸，返回路径
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/url")
	public String faceUrl(@RequestBody String imageInfo) throws Exception {

		String fileName = UUIDUtil.getUUID().toString() + (new Date()).getTime() + ".png";
		// 切分字符串
		String[] info = imageInfo.split(",");
		// 制作回传图片
		String path = info[2];
		String img = info[1];
		String userId=info[3];
		Boolean flag = GenerateImage(img, path, fileName);
		String result = "";
		String faceToken = faceUtil.getFaceTokenAR(img,userId);
		if (faceToken == null) {
			return null;
		}
		if (flag) {
			result = "http://image.oa.com/" + fileName + "," + faceToken;
		}
		return result;

	}

	/**
	 * 注册人脸
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping("/regist")
	public int faceRegist(@RequestBody String userStr) throws Exception {
		User user = ObjectUtil.mapper.readValue(userStr, User.class);
		int updateUserImg = userMapper.updateUserImg(user);

		return updateUserImg;

	}

	/**
	 * 人脸对比
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/book")
	public int book(@RequestBody String info) throws Exception {
		
		String image = info.split(",")[0];
		String userId = info.split(",")[1];
		String token = userMapper.getToken(userId);
		if (faceUtil.isEmp(token, image)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String checkTime = format.format(new Date());
			//拼接数据为图表做准备
			String[] split = checkTime.split(" ");
			String[] split2 = split[1].split(":");
			int h = Integer.parseInt(split2[0]);
			int m = Integer.parseInt(split2[1]);
			int s = Integer.parseInt(split2[2]);
			long total=h*60*60+m*60+s;
			checkTime=split[0]+" "+total;
			Integer addCheck = checkMapper.addCheck(userId,checkTime);
			if (addCheck > 0) {
				return 1;
			}

		}
		return 0;
	}
	/**
	 * 获取往日10天的登录数据
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/getData")
	public List<String> getData(@RequestBody String userId) throws JsonProcessingException{
		List<String> data = userMapper.getData(userId);
		return data;
	}

	/**
	 * 生成回传图片
	 * 
	 * @param imgStr
	 * @param filePath
	 * @param fileName
	 * @return
	 */

	public boolean GenerateImage(String imgStr, String filePath, String fileName) {
		try {
			if (imgStr == null) {
				return false;
			}
			Base64Util decoder = new Base64Util();
			byte[] b = decoder.decode(imgStr);

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			OutputStream out = new FileOutputStream(filePath + "/" + fileName);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
