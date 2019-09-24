package com.etranzact.config;

import com.etranzact.messaging.kafka.consumer.SourceOutput;
import com.etranzact.messaging.kafka.producer.SourceInput;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Configuration
@EnableConfigurationProperties
@EnableBinding(value = {SourceInput.class, SourceOutput.class})
public class Appconfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
