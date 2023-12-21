package cholog.di;

import org.springframework.stereotype.Component;

@Component
public class InjectionBean {
    public String hello() {
        return "Hello";
    }
}
