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
	 * ��ʼ�� MongoTemplate MongoTemplate �Ĺ������ж��֣�<br/>
	 * ���� MongoTemplateʱ��������ѡ�<br/>
	 * WriteResultCheckingPolicy,WriteConcern,ReadPreference<br/>
	 * WriteResultCheckingPolicy ���յ�moongoDB ���쳣ʱ������ԣ�LOG EXCEPTION NONE<br/>
	 * WriteConcern ���� Write��֪ͨ-�о���mongoDB������ش�ģ�0:���ȷ�������Ӧ 1:�ȴ�֪ͨ�����ǲ��ȴ��ӷ������ĸ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "tm");
	}
}
