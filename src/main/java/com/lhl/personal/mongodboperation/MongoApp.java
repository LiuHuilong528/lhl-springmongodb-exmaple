package com.lhl.personal.mongodboperation;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
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
			mongoOps.updateFirst(Query.query(where("name").is("Joe")), Update.update("age", 35), Person.class);
			p = mongoOps.findOne(Query.query(where("name").is("Joe")), Person.class);
			log.info("Updated: " + p);

			// Delete
			mongoOps.remove(p);

			// Check that deletion worked
			List<Person> people = mongoOps.findAll(Person.class);
			log.info("Number of people = : " + people.size());

			// findAddUpdate sample
			mongoOps.insert(new Person("Tom", 22));
			mongoOps.insert(new Person("Dicky", 22));
			mongoOps.insert(new Person("Harry", 22));
			mongoOps.insert(new Person("Susan", 22));
			mongoOps.insert(new Person("Kobe", 32));

			Query query = new Query(where("name").is("Harry"));
			Update update = new Update().inc("age", 2);
			// findAndModify 返回query查到的记录，更新前的 age-22
			Person p1 = mongoOps.findAndModify(query, update, Person.class);
			System.out.println(p1.getAge());

			// 更新后的 记录
			p = mongoOps.findOne(query, Person.class);
			System.out.println(p.getAge());

			// 筛选字段，不用返回全部的字段 - 建造者模式
			query.fields().exclude("id");
			// 此操作对比之前的 返回的 是更新后的记录
			p1 = mongoOps.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Person.class);
			System.out.println(p1.getAge());

			// Example Query - Repository 实现QueryByExampleExecutor 接口
			Person person = new Person("Cart", 40);
			// 默认的匹配模式方法
			Example<Person> example = Example.of(person);

			// 自定义匹配模式
			ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase("name").withIncludeNullValues();
			Example<Person> ex = Example.of(person, matcher);

			// MapReduce 操作  读取js 文件操作数据库获取结果
			MapReduceResults<Person> results = mongoOps.mapReduce("jmr1", "classpath:map.js", "classpath:reduce.js",
					Person.class);
			
			// 聚合查询 - Aggregation
			Aggregation aggregation = Aggregation.newAggregation((List<? extends AggregationOperation>) new AggregationOptions(false, false, null));
			
			// 删除表格
			mongoOps.dropCollection(Person.class);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}