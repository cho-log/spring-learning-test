package cholog.utils;

import cholog.SpringCoreApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ContextUtils {
    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    public static ApplicationContext getApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringCoreApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        return context;
    }
    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    public static ApplicationContext getApplicationContext(Class clazz) {
        ApplicationContext context = new AnnotationConfigApplicationContext(clazz);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        return context;
    }
}
