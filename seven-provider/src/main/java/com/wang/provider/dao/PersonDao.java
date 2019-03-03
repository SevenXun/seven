package com.wang.provider.dao;

import com.wang.provider.dao.entity.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {

    List<Person> list();


//    @Select("select * from person where id=#{id}")
    Person getById(@Param("id")long id);

    Person getByTableAndId(@Param("id")long id,@Param("tableName")String tableName);

}
