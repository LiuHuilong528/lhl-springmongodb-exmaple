package com.lhl.personal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * {@link Service} with hard-coded input data.
 */
@Component
public class ExampleService implements Service {
	private static final Logger log = LoggerFactory.getLogger(ExampleService.class);
	
	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		log.info("ExampleService");
		return "Hello world!";	
	}

}
