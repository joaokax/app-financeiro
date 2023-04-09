package com.example.appfinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
public class AppFinanceiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppFinanceiroApplication.class, args);
        System.out.println("Documentation :  http://localhost:8080/swagger-ui/index.html#");
    }

}
