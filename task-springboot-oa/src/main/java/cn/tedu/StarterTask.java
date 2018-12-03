package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tedu.task.mapper")
public class StarterTask {
	public static void main(String[] args) {
		
		SpringApplication.run(StarterTask.class,args);
	}
	
}
