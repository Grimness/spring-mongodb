package com.mongo.service;

import com.mongo.dao.domain.Person;

import java.util.List;

/**
 * Created by wangbinghua on 17-1-18.
 */
public interface PersonService {

    public List<Person> findAllPerson();

}
