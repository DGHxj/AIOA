package cn.tedu.common.service;

import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@Service
public class RedisClusterService{

	@Autowired
	private JedisCluster redis;

	public void set(String key, String value) {
		redis.set(key, value);

	}

	public void set(String key, String value, Integer second) {
		redis.expire(key, second);
	}

	public String get(String key) {
		String result = redis.get(key);
		return result;
	}

	public boolean exists(String key) {
		Boolean exists = redis.exists(key);
		return exists;
	}

	public void del(String key) {
		redis.del(key);
	}

	public TreeSet<String> keys(String pattern) {
		TreeSet<String> keys = new TreeSet<>();
		//获取集群节点对象集
		Map<String, JedisPool> clusterNodes = redis.getClusterNodes();
		//便历每一个节点
		for (String k : clusterNodes.keySet()) {
			//得到每一个节点的连接池
			JedisPool jp = clusterNodes.get(k);
			//得到一个连接
			Jedis connection = jp.getResource();
			try {
				//获取key-value数据的key集
				keys.addAll(connection.keys(pattern));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();// 用完一定要close这个链接！！！
			}
		}
		//返回这个key集
		return keys;
	}

}
