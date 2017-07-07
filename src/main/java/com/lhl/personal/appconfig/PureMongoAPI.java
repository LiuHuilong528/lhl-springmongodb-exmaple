package com.lhl.personal.appconfig;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

//@Configuration
public class PureMongoAPI  extends MongoJavaConfig{

	//1 原生 MongoClient 
	public @Bean Mongo mongo() throws UnknownHostException{
		return new MongoClient("192.168.1.55",27018);
	}
}
