package com.ambev.projetopratico5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.ambev.techempowers.repository")
@ComponentScan(basePackages = "com.ambev.techempowers")
public class TestMongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        String testMongoUri = "mongodb://0.0.0.0:27017/produto";
        return new MongoTemplate(
                new SimpleMongoClientDatabaseFactory(testMongoUri));
    }
}
