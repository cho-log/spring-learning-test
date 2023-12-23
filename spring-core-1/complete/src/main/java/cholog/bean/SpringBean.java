package cholog.bean;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {
    public String hello() {
        return "Hello";
    }
}
