package cholog.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    @Autowired
    private SpringBean springBean;

    public String sayHello() {
        return springBean.hello();
    }
}
