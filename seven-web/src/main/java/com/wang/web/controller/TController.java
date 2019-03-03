package com.wang.web.controller;

import com.wang.provider.dao.entity.Person;
import com.wang.provider.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/t")
public class TController {
    @Autowired
    PersonServiceImpl personService;

    @RequestMapping("/get")
    List<Person> list(){
        List<Person> personList = personService.list();
        return personList;
    }

    @RequestMapping("/getById")
    Person getById(@RequestParam("id")long id){
        Person person = personService.getById(id);
        return person;

    }
}
