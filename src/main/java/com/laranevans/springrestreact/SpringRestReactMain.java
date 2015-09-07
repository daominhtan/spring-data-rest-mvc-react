package com.laranevans.springrestreact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Scan the whole application for beans.
@ComponentScan(basePackages = "com.laranevans.springrestreact")
public class SpringRestReactMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestReactMain.class, args);
    }
}
