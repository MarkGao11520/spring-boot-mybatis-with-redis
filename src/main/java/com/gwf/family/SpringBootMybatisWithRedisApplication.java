package com.gwf.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootMybatisWithRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisWithRedisApplication.class, args);
    }
}
