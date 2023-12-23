package cholog.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldInjection {
    @Autowired
    private InjectionBean injectionBean;

    public String sayHello() {
        return injectionBean.hello();
    }
}
