package com.fiber.demo.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author panyox
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
public class DemoDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDubboApplication.class, args);
    }
}
