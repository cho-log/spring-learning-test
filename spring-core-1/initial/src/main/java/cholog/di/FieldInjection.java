package cholog.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldInjection {

    /*
    FieldInjection으로 InjectionBean 주입받기
     */
    private InjectionBean injectionBean;

    public String sayHello() {
        return injectionBean.hello();
    }
}
