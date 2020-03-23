package demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
/*
    start cache true:cglib
 */
@EnableCaching(proxyTargetClass = true)
@EnableAdminServer
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
