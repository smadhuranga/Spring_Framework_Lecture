package lk.ijse.config;

import lk.ijse.bean.SpringBean;
import lk.ijse.bean.SpringBean1;
import org.springframework.context.annotation.*;

@Configuration
@Import({AppConfig1.class, AppConfig2.class})
////Root
//@ImportResource("classpath:hibernate.xml")
////if not
//@ImportResource("file:absolut-path-of-hibernate.xml")
public class AppConfig {

    @Bean
    public SpringBean springBean(){
        return new SpringBean();
    }

}
