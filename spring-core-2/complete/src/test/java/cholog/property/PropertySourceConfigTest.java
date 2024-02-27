package cholog.property;

import cholog.property.config.PropertySourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PropertySourceConfigTest {

    @Test
    void getPropertyValueFromUsingEnvironment() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        RestClient restClient = context.getBean(GoogleMapsRestClient.class);
        assertThat(restClient.getEndpoint()).isEqualTo("https://www.googleapis.com");
    }

    @Test
    void getPropertyValueFromUsingAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        RestClient restClient = context.getBean(GoogleDriveRestClient.class);
        assertThat(restClient.getEndpoint()).isEqualTo("https://www.googleapis.com");
    }

}
