package com.lhl.personal.appconfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.lhl.personal.person.Person;
import com.mongodb.MongoClient;

//@Configuration
public class AppMongoDB {
	private static final Log log = LogFactory.getLog(AppMongoDB.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(new MongoClient("192.168.1.55",27018), "tm");
	    mongoOps.insert(new Person("Joe", 34));

	    log.info(mongoOps.findOne(new Query(Criteria.where("name").is("Joe")), Person.class));

	    mongoOps.dropCollection("person");
	  }
}
