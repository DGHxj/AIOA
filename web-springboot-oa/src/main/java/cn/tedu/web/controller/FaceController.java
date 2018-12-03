package cn.tedu.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.common.util.ObjectUtil;
import cn.tedu.common.util.VoiceUtil;
import cn.tedu.web.pojo.User;
import cn.tedu.web.service.FaceService;

@Controller
@RequestMapping("/face")
public class FaceController {

	@Autowired
	private FaceService faceService;

	/**
	 * 人脸回显并返回face-token
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/url")
	@ResponseBody
	public String faceRegist(String image, HttpServletRequest req, HttpServletResponse resp) throws Exception {

		if (image == null) {
			return null;
		}
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String path = req.getServletContext().getRealPath("/upload");
		String regist = faceService.savePic(image, path, user.getUserId());
		System.out.println(regist);
		String[] split = regist.split(",");
		String token = split[1].substring(1, split[1].length() - 1);

		return split[0] + "," + token;
	}

	/**
	 * 人脸注册
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String faceRegist(User user) throws Exception {
		if (user == null) {
			return null;
		}
		int s = faceService.regist(user);
		if (s > 0) {
			return ObjectUtil.mapper.writeValueAsString("注册成功！");
		}
		return ObjectUtil.mapper.writeValueAsString("注册失败！");
	}

	/**
	 * 人脸识别，实现签到
	 */
	@RequestMapping("/book")
	@ResponseBody
	public String faceBook(String image, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (image == null) {
			VoiceUtil.play(user.getUserName() + "签到失败！", "5", "5", "4");
			return ObjectUtil.mapper.writeValueAsString("签到失败！");
		}
		boolean s = faceService.book(image, user.getUserId());
		if (s) {
			VoiceUtil.play(user.getUserName() + "签到成功！", "5", "5", "4");
			return ObjectUtil.mapper.writeValueAsString("签到成功！");
		}
		VoiceUtil.play(user.getUserName() + "签到失败！", "5", "5", "4");
		return ObjectUtil.mapper.writeValueAsString("签到失败！");
	}

	/**
	 * 统计数据
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/weekData")
	@ResponseBody
	public String faceData(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<String> list = faceService.getFaceData(user.getUserId());
		String str="签到日期,签到时间\r\n";
		for(String data:list){
			String[] split = data.split(" ");
			str+=split[0]+","+split[1]+"\r\n";
			
		}
		//str = "Day,签到时间\r\n3/9/13,7:\r\n3/10/13,15:36:00\r\n3/11/13,15574\r\n3/12/13,16211\r\n3/13/13,16427\r\n3/14/13,5691\r\n3/15/13,5430\r\n3/16/13,15574\r\n3/17/13,16211\r\n3/18/13,16427\r\n";
		return ObjectUtil.mapper.writeValueAsString(str);
	}

}
