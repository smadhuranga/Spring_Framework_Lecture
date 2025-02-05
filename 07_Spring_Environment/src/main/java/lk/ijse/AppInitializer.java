package lk.ijse;

import lk.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Properties;

public class AppInitializer {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);

        context.refresh();
//System Variables
//        Map<String, String> env = System.getenv();
//        for (String key : env.keySet()) {
//            System.out.println(key + " : " + env.get(key));
//        }
        //Java Properties
//        Properties properties = System.getProperties();
//        for (String key : properties.stringPropertyNames()) {
//            System.out.println(key + " : " + properties.get(key));
//        }
//        String osName = System.getProperty("os.name");
//        System.out.println(osName);




        context.registerShutdownHook();

    }
}