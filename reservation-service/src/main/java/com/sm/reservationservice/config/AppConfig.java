package com.sm.reservationservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /*
     *  Didn't work with Zipkin as it provided due to some issue with RestTemplate
     *
     * Refer - https://stackoverflow.com/a/77347961/10871900
     *
     * */
//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }
}
