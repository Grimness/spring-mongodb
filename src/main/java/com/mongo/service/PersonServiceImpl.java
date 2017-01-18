package com.mongo.service;

import com.mongo.dao.PersonRepository;
import com.mongo.dao.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangbinghua on 17-1-18.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }
}
