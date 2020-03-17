package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/*
    start cache true:cglib
 */
@EnableCaching(proxyTargetClass = true)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
