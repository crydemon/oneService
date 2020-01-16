package com.vova.trackingupload;

import com.vova.trackingupload.property.FileStorageProperties;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@EnableConfigurationProperties({
        FileStorageProperties.class
})

@SpringBootApplication
public class OneServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(OneServiceApplication.class, args);
    }
}
