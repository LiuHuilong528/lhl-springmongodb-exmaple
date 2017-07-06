package com.lhl.personal.appconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.lhl.personal.person.Person;
import com.mongodb.MongoClient;

public class MongoApp {

	private static final Logger log = LoggerFactory.getLogger(MongoApp.class);

	public static void main(String[] args) throws Exception {
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("192.168.1.55",27018), "tm"));
		mongoOps.insert(new Person("JOE",34));
		log.info(mongoOps.findOne(new Query(Criteria.where("name").is("JOE")),Person.class).toString());
		mongoOps.dropCollection(Person.class);
	}
}
