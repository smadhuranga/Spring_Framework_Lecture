package lk.ijse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {

//@Autowired(required = false)
//    public SpringBean(@Value("Supun") String name,@Value("1") int id){
//        System.out.println("SpringBean");
//        System.out.println(name);
//        System.out.println(id);
//    }
//    @Autowired
//    public SpringBean(@Value("Supun") String name,@Value("1") int id,@Value("1") int age){
//        System.out.println("SpringBean");
//        System.out.println(name);
//        System.out.println(id);
//        System.out.println(age);
//    }
    @Value("Supun")
    private String name;

    public SpringBean() {
        System.out.println(name);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
    }
}
