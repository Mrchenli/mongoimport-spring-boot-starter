package com.kiibos.mongoexport.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MongoExportAutoConfigure
 * @Description TODO
 * @Author cl
 * @Date 2018/12/31 下午9:03
 **/
@Configuration
@ConditionalOnProperty(prefix = "spring.mongo.import",value = "enabled",havingValue = "true")
@ComponentScan(basePackages = "com.kiibos.mongoexport")
public class MongoExportAutoConfigure {



}
