package com.azh;

import com.azh.config.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@ComponentScan(basePackages = {"com.azh.*"})
@EnableAutoConfiguration*/

// 组合注解
@SpringBootApplication
public class SpringBootConfigFileApplication {
    public static void main(String[] args) {
        // ----设置 Banner 是否启动 start----
        SpringApplication application = new SpringApplication(SpringBootConfigFileApplication.class);
        application.setBanner(new MyBanner());
        application.run(args);
        // ---- end----

        // ----默认启动 start----
        //SpringApplication.run(FirstSpringBootPrjApplication.class, args);
        // ----end----
    }
}
