package cholog;

import cholog.di.ConstructorInjection;
import cholog.di.FieldInjection;
import cholog.di.SetterInjection;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

public class DependencyInjectionTest {
    @Test
    void constructorInjection() {
        ApplicationContext context = getApplicationContext();
        ConstructorInjection service = context.getBean("constructorInjection", ConstructorInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }

    @Test
    void setterInjection() {
        ApplicationContext context = getApplicationContext();
        SetterInjection service = context.getBean("setterInjection", SetterInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }

    @Test
    void autowiredInjection() {
        ApplicationContext context = getApplicationContext();
        FieldInjection service = context.getBean("fieldInjection", FieldInjection.class);
        assertThat(service.sayHello()).isEqualTo("Hello");
    }
}
