package com.get.hyphenbackendinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HyphenBackendInquiryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyphenBackendInquiryApplication.class, args);
    }

}
