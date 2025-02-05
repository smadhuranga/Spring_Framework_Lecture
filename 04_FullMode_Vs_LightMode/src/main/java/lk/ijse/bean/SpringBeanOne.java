package lk.ijse.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanOne  {
    @Bean
    public SpringBeanTwo springBeanTwo() {
        SpringBeanThree beanThree1 =springBeanThree();
        SpringBeanThree beanThree2 =springBeanThree();
        System.out.println(beanThree1);
        System.out.println(beanThree2);

        return new SpringBeanTwo();
    }
    @Bean
    public SpringBeanThree springBeanThree() {
        return new SpringBeanThree();
    }


}
