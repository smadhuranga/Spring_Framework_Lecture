package lk.ijse.condig;

import lk.ijse.bean.MyConnetion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "lk.ijse.bean")
public class AppConfig {
    @Bean("connetion")
    @Scope("singleton")
    MyConnetion getConnetion(){
        return new MyConnetion();
    }

}
