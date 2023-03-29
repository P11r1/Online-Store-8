package com.example.onlinestorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

public class OnlineStoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreBackendApplication.class, args);
        System.out.println("Started??");
    }

}
