package cn.tedu.common.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class RedisClusterConfiguration {
	@Value("${spring.redis.nodes:null}")
	private String nodes;
	@Value("${spring.redis.pool.max-idle:1}")
	private Integer maxIdle;
	@Value("${spring.redis.pool.min-idle:0}")
	private Integer minIdle;
	@Value("${spring.redis.pool.max-active:1}")
	private Integer maxTotal;//对应配置文件时max-active
	@Value("${spring.redis.pool.max-wait:1}")
	private Integer maxWait;
	@Value("${spring.redis.timeout:0}")
	private Integer timeout;
	
	@Bean 
	public JedisCluster getInstance(){
		//收集节点信息
		Set<HostAndPort> infoList=new HashSet<HostAndPort>();
		String[] node = nodes.split(",");
		
		for(String hostAndPort:node){
			String ip = hostAndPort.split(":")[0];
			Integer port = Integer.parseInt(hostAndPort.split(":")[1]);
			infoList.add(new HostAndPort(ip, port));
		}
		//配置config
		GenericObjectPoolConfig config=new GenericObjectPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setMinIdle(minIdle);
		
		JedisCluster jedis=new JedisCluster(infoList,config);				
		return jedis;
	}	
}












