package lk.ijse.bean;

import org.springframework.stereotype.Component;

@Component
public class Girl2 implements Agreement {
    public Girl2() {
        System.out.println("Girl Created 2");
    }

    @Override
    public void chat() {
        System.out.println("Girl chat 2");
    }
}
