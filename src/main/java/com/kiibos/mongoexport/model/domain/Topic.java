package com.kiibos.mongoexport.model.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName Topic
 * @Description TODO
 * @Author cl
 * @Date 2018/12/28 下午3:18
 **/
@Data
public class Topic {

    @Id
    private String topicId=UUID.randomUUID().toString();


    private String channel;

    private String title;

}
