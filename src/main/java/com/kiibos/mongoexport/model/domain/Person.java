package com.kiibos.mongoexport.model.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @ClassName Person
 * @Description TODO
 * @Author cl
 * @Date 2018/12/28 下午4:04
 **/
@Data
public class Person {

    @Id
    ObjectId id;

    @Field("first_name") private String firstname;

    @Field("last_name") private String lastname;

    private int age;

    public Person(String firstname, String lastname, int age) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

}
