package cholog.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowiredBean {
    /*
    어떤 방법으로 Component에 Bean을 주입하는지 학습하기
     */
    private SpringBean springBean;

    public String sayHello() {
        return springBean.hello();
    }
}
