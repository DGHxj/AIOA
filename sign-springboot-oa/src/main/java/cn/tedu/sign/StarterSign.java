package cn.tedu.sign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("cn.tedu.sign.mapper")
public class StarterSign {
	public static void main(String[] args) {
		SpringApplication.run(StarterSign.class, args);
	}
}
