package lk.ijse.bean;

import org.springframework.stereotype.Component;

@Component

public class SpringBean {

    public SpringBean() {
        System.out.println("SpringBean Created");
    }
    public void testBean(){
        System.out.println("testBean");
    }
}
