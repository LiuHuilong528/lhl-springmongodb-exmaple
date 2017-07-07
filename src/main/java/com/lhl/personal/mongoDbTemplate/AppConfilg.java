package com.lhl.personal.mongoDbTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class AppConfilg {

	public @Bean Mongo mongo() throws Exception {
		return new MongoClient("192.168.1.55", 27018);
	}

	/**
	 * 初始化 MongoTemplate MongoTemplate 的构造器有多种：<br/>
	 * 创建 MongoTemplate时，其他的选项：<br/>
	 * WriteResultCheckingPolicy,WriteConcern,ReadPreference<br/>
	 * WriteResultCheckingPolicy 接收到moongoDB 的异常时处理策略：LOG EXCEPTION NONE<br/>
	 * WriteConcern 控制 Write的通知-感觉与mongoDB链接相关大的：0:不等服务器响应 1:等待通知，但是不等待从服务器的复制
	 * WriteConcernResolver - 为每个不同的操作设定不同的 WriteConcern <br/>
	 * ReadPreference - 读写分离设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "tm");
	}

}
