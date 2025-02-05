package lk.ijse;

import lk.ijse.bean.Boy;
import lk.ijse.config.AppConfig;
import lk.ijse.di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        Test2 test2 = context.getBean(Test2.class); //Test2
        test2.display();

        context.registerShutdownHook();

    }
}