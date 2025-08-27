package br.com.davisoares.designpatternsdio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
        System.out.println("SPRING_PROFILES_ACTIVE: " + System.getenv("SPRING_PROFILES_ACTIVE"));
        System.out.println("PGHOST: " + System.getenv("PGHOST"));
        System.out.println("PGPORT: " + System.getenv("PGPORT"));
        System.out.println("PGDATABASE: " + System.getenv("PGDATABASE"));
        System.out.println("PGUSER: " + System.getenv("PGUSER"));
        System.out.println("PGPASSWORD: " + System.getenv("PGPASSWORD") );
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

}
