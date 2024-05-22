package cholog;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
    @Bean
    public TodoClientWithRestTemplate todoClient(RestTemplateBuilder restTemplateBuilder) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return new TodoClientWithRestTemplate(restTemplate);
    }

    @Bean
    public TodoClientWithRestClient todoRestClient() {
        return new TodoClientWithRestClient(
                RestClient.builder().baseUrl("http://jsonplaceholder.typicode.com").build()
        );
    }
}
