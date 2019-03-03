package com.wang.web.controller;

import com.wang.provider.dao.entity.Person;
import com.wang.provider.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping("/get")
    public List<Person> get() {
        List<Person> personList = personService.list();
        return personList;
    }

}
