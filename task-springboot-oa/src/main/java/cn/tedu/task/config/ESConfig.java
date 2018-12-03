package cn.tedu.task.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig implements 
InitializingBean,FactoryBean<TransportClient>{
	@Value("${cluster-name}")
	private String name;
	@Value("${cluster-nodes}")
	private String nodes;
	
	private TransportClient client;
	//对象的初始化方法,将当前类中的某个值进行初始化,TransportClient对象
	//属于initializingBean的方法
	@Override
	public void afterPropertiesSet() throws Exception {
		//nodes有可能是多个ip:port
		String[] node=nodes.split(",");//{"10.9.9.9:9200","*.*.*.*:**"}
		//设置一下settings
		Settings set=Settings.builder().
				put("cluster.name",name).build();
		client=new PreBuiltTransportClient(set);
		for (String hostAndPort : node) {
			String host=hostAndPort.split(":")[0];
			int port=Integer.parseInt(hostAndPort.split(":")[1]);
			client.addTransportAddress(
					new InetSocketTransportAddress(
						InetAddress.getByName(host),port));
		}
	}
	//框架当有getObject方法配合getObjectType的方法返回的对象时
	//注入transportClient时,就调用getObject返回对象,已经被初始化完成的
	
	@Override
	public TransportClient getObject() throws Exception {
		return client;
	}
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return TransportClient.class;
	}
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

