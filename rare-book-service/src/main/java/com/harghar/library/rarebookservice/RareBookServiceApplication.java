package com.harghar.library.rarebookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RareBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RareBookServiceApplication.class, args);
    }
}
