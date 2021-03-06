package com.ascendix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("application-local.properties")
public class Main {
    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }
}
