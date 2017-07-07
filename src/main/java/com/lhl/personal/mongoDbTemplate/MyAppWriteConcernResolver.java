package com.lhl.personal.mongoDbTemplate;

import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.WriteConcernResolver;

import com.mongodb.WriteConcern;

/**
 * WriteConcernResolver 使用示例
 * 
 * @author datatoucher
 *
 */
public class MyAppWriteConcernResolver implements WriteConcernResolver {

	@Override
	public WriteConcern resolve(MongoAction action) {
		if (action.getEntityType().getSimpleName().contains("Audit")) {
			return WriteConcern.UNACKNOWLEDGED;
		} else if (action.getEntityType().getSimpleName().contains("Metadata")) {
			return WriteConcern.JOURNAL_SAFE;
		}
		return action.getDefaultWriteConcern();
	}

}
