package cholog.property.config;

import cholog.property.JwtTokenKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {
    @Bean
    public JwtTokenKeyProvider jwtTokenKeyProvider(@Value("${security.jwt.token.secret-key}") String secretKey) {
        return new JwtTokenKeyProvider(secretKey);
    }
}
