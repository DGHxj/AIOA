package cn.tedu.sign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.face.AipFace;

@Configuration
public class AipFaceConfig {
	@Value("${APP_ID}")
	private String APP_ID;
	@Value("${API_KEY}")
	private String API_KEY;
	@Value("${SECRET_KEY}")
	private String SECRET_KEY;

	private AipFace client;

	@Bean
	public AipFace getInstance() {
		// 初始化一个AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(20000);
		client.setSocketTimeoutInMillis(80000);

		return client;
	}
}
