package com.mongo.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by wangbinghua on 17-1-15.
 */
@Configuration
@ComponentScan(value="com.mongo",excludeFilters=@ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = {Controller.class, EnableWebMvc.class, ControllerAdvice.class}))
@Import({MongoDBConf.class})
public class SpringContextConf {
}
