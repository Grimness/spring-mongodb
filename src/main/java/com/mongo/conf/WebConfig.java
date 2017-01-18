package com.mongo.conf;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by wangbinghua on 17-1-15.
 */
public class WebConfig implements WebApplicationInitializer {

    //相当于 web.xml 文件配置

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        //注册 SpringContext.xml
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.setConfigLocation("classpath:beans.xml");
        applicationContext.register(SpringContextConf.class);
        //注册监听器(启动Spring Context 上下文)
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(applicationContext);
        servletContext.addListener(contextLoaderListener);


        //动态注册 filter
        //注册 HiddenHttpMethodFilter 使用Restful服务，屏蔽get post方法
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        FilterRegistration.Dynamic filterRegistration =
                servletContext.addFilter("HiddenHttpMethodFilter", hiddenHttpMethodFilter);
        filterRegistration.addMappingForServletNames(
                EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE),
                false,
                "dispatcherServlet");

        //SpringMVC.xml
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(SpringWebConf.class);

        //动态注册Spring mvc DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcherServlet",dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

    }


}
