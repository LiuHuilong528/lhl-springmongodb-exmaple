package com.lhl.personal.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

public class FactoryBeanMongoAPI extends MongoJavaConfig {

	// 2 利用MongoClientFactoryBean
	public @Bean MongoClientFactoryBean mongo() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost("192.168.1.55");
		mongo.setPort(27018);
		return mongo;
	}
}
