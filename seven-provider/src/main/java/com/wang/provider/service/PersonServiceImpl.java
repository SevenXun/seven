package com.wang.provider.service;

import com.wang.provider.dao.PersonDao;
import com.wang.provider.dao.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl {
    @Autowired
    private PersonDao personDao;

    public List<Person> list() {
        return personDao.list();
    }

    public Person getById(long id){
        return personDao.getById(id);
    }
}
