package com.azh;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/*@ComponentScan(basePackages = {"com.azh.*"})
@EnableAutoConfiguration*/

// 组合注解
@SpringBootApplication
public class FirstSpringBootPrjApplication {
    public static void main(String[] args) {
        // ----设置 Banner 是否启动 start----
        SpringApplicationBuilder banner = new SpringApplicationBuilder(FirstSpringBootPrjApplication.class);
//        banner.application().setAdditionalProfiles("dev");

        banner.bannerMode(Banner.Mode.LOG).run(args);

        // ---- end----

        // ----默认启动 start----
        //SpringApplication.run(FirstSpringBootPrjApplication.class, args);
        // ----end----
    }
}
