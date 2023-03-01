package com.aurosks.kolesnyk;

import com.aurosks.kolesnyk.config.AppConfig;
import com.aurosks.kolesnyk.controller.PackageController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        DataSource dataSource = context.getBeansOfType(DataSource.class)
                .entrySet().iterator().next().getValue();
        System.out.println(dataSource);

        context.getBeansOfType(Object.class).entrySet()
                .forEach(System.out::println);
    }
}