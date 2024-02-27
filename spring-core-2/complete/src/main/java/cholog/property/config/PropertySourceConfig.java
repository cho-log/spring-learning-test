package cholog.property.config;

import cholog.property.JwtTokenKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기
@Configuration
@PropertySource("classpath:token.properties")
public class PropertySourceConfig {

    private final Environment env;

    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    // TODO: token.properties의 security.jwt.token.secret-key 값을 활용하여 JwtTokenKeyProvider를 빈으로 등록하기
    @Bean
    @Primary
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        return new JwtTokenKeyProvider(env.getProperty("security.jwt.token.secret-key"));
    }

    @Bean
    public JwtTokenKeyProvider jwtTokenKeyProvider2(@Value("${security.jwt.token.secret-key}") String secretKey) {
        return new JwtTokenKeyProvider(secretKey);
    }
}
