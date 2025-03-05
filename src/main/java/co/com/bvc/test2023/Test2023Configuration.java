package co.com.bvc.test2023;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Test2023Configuration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
