package com.mongo.dao;

import com.mongo.dao.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wangbinghua on 17-1-15.
 */
public interface PersonRepository extends MongoRepository<Person,String>{
}
