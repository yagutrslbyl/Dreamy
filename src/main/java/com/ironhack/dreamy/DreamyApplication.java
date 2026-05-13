package com.ironhack.dreamy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DreamyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamyApplication.class, args);
        System.out.println("Dreamy Application started");
    }

}
