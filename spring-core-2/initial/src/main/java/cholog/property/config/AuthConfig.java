package cholog.property.config;

import cholog.property.JwtTokenKeyProvider;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
public class AuthConfig {
    // TODO: application.properties의 security.jwt.token.secret-key 값을 활용하여 JwtTokenKeyProvider를 빈으로 등록하기
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        return new JwtTokenKeyProvider("");
    }
}
