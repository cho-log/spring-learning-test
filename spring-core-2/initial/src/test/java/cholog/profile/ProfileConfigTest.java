package cholog.profile;

import cholog.profile.config.ProfileConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileConfigTest {

    @Test
    public void shouldCreateInmemoryRepositoryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfig.class);
        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        MessageRepository service = context.getBean(MessageRepository.class);
        assertThat(service).isNotNull();
        assertThat(service.getClass()).isEqualTo(InmemoryMessageRepository.class);
    }

    @Test
    public void shouldCreateJdbcRepositoryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(ProfileConfig.class);
        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        MessageRepository service = context.getBean(MessageRepository.class);
        assertThat(service).isNotNull();
        assertThat(service.getClass()).isEqualTo(JdbcMessageRepository.class);
    }

}
