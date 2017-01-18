package com.mongo.web;

import com.mongo.dao.domain.Person;
import com.mongo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangbinghua on 17-1-15.
 */
@Controller
public class TestController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/allPersons")
    @ResponseBody
    public List<Person> getAllPerson(){
        return personService.findAllPerson();
    }

}
