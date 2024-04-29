package cholog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    // TODO: "/" 요청 시 hello.html 페이지 응답하기
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    // TODO: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    // TODO: AuthenticationPrincipalArgumentResolver 등록하기
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }
}
