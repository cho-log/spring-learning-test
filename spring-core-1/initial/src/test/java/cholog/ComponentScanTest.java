package cholog;

import cholog.scan.ComponentScanBean;
import cholog.scan.ContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static cholog.utils.ContextUtils.getApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

public class ComponentScanTest {

    @Test
    void scanComponent() {
        ApplicationContext context = getApplicationContext(ContextConfiguration.class);
        ComponentScanBean componentScanBean = context.getBean("componentScanBean", ComponentScanBean.class);
        assertThat(componentScanBean).isNotNull();
    }
}
