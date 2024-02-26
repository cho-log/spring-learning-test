package cholog.config;

import cholog.SpringCoreApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {
    @Test
    void createBean() {
         ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
         String[] beanDefinitionNames = context.getBeanDefinitionNames();
         System.out.println(Arrays.toString(beanDefinitionNames));

         AuthService authService = context.getBean(AuthService.class);
         assertThat(authService).isNotNull();
    }

    @Test
    void createBeansThatHasDependency() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        AuthenticationPrincipalArgumentResolver resolver = context.getBean(AuthenticationPrincipalArgumentResolver.class);
        assertThat(resolver).isNotNull();
        assertThat(resolver.getAuthService()).isNotNull();

        AuthService authService = context.getBean(AuthService.class);
        assertThat(resolver.getAuthService()).isEqualTo(authService);
    }
}
