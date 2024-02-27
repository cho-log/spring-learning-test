package cholog.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")
    public MessageRepository inMemoryMessageRepository() {
        return new InmemoryMessageRepository();
    }

    @Bean
    @Profile("prod")
    public MessageRepository jdbcMessageRepository() {
        return new JdbcMessageRepository();
    }

}
