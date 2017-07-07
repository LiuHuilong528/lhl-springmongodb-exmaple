package com.lhl.personal.mongodboperation;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;


public class MongoApp {

	private static final Log log = LogFactory.getLog(MongoApp.class);

	public static void main(String[] args) {
		try {
			MongoClient mongo = new MongoClient("192.168.1.55", 27018);
			MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(mongo, "classiyfy"));
			Person p = new Person("Joe", 34);

			// insert
			mongoOps.insert(p);
			log.info("insert:" + p);

			// Find
			p = mongoOps.findById(p.getId(), Person.class);
			log.info("Found: " + p);

			// Update
			mongoOps.updateFirst(Query.query(Criteria.where("name").is("Joe")), Update.update("age", 35), Person.class);
			p = mongoOps.findOne(Query.query(Criteria.where("name").is("Joe")), Person.class);
			log.info("Updated: " + p);
			
		    // Delete
		    mongoOps.remove(p);

		    // Check that deletion worked
		    List<Person> people =  mongoOps.findAll(Person.class);
		    log.info("Number of people = : " + people.size());

		    // 删除表格
		    mongoOps.dropCollection(Person.class);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}