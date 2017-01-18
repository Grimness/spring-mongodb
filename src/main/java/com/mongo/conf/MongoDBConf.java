package com.mongo.conf;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangbinghua on 17-1-15.
 */
@Configuration
@PropertySource(value = "classpath:mongodb.properties")
@EnableMongoRepositories(basePackages = "com.mongo.dao")
public class MongoDBConf {

    @Autowired
    private Environment environment;

    @Bean
    public MongoClient mongoClient(){
        String username = environment.getProperty("mongo.username");
        String password = environment.getProperty("mongo.password");
        String database = environment.getProperty("mongo.database");
        String host = environment.getProperty("mongo.host");
        Integer port = Integer.valueOf(environment.getProperty("mongo.port"));
        ServerAddress serverAddress = new ServerAddress(host,port);
        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(mongoCredential);

        return new MongoClient(serverAddress,mongoCredentialList);
    }



    @Bean
    public MongoDbFactory mongoDbFactory(){
        String database = environment.getProperty("mongo.database");
        return new SimpleMongoDbFactory(mongoClient(),database);
    }

    @Bean
    public MongoTemplate mongoTemplate(){

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }


}
