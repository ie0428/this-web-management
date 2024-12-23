package com.itheima.t;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ThisWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThisWebManagementApplication.class, args);
    }

}
