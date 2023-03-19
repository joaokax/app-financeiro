package com.example.appfinanceiro;

//import com.example.appfinanceiro.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableMongoRepositories
public class AppFinanceiroApplication {
//    public AppFinanceiroApplication(UserService userService) {
//        this.userService = userService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(AppFinanceiroApplication.class, args);
        System.out.println("Documentation :  http://localhost:8080/swagger-ui/index.html#");
    }

//    private final UserService userService;
//
//    public void run(String... args) throws Exception {
//        this.userService.save(
//                "Joao Kax",
//                "joaokax@email.com",
//                "123456");
//    }

}
