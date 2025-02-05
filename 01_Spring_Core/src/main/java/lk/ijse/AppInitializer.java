package lk.ijse;

import lk.ijse.bean.MyConnetion;
import lk.ijse.bean.SpringBean;
import lk.ijse.bean.TestBean1;
import lk.ijse.bean.TestBean2;
import lk.ijse.condig.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
//
//        SpringBean bean = context.getBean(SpringBean.class);
//        System.out.println(bean);
//        bean.testBean();
//
////        Runtime.getRuntime().addShutdownHook(new Thread(){
////            @Override
////            public void run () {
////                System.out.println("Shutting down the application");
////                context.close();
////        }
////        });
//
//        Object bean1 = context.getBean("testBean1");
//        System.out.println(bean1);
//
//        SpringBean bean2 =(SpringBean) context.getBean("springBean");
//        System.out.println(bean2);
//
//
//
//        TestBean1 testBean1 = context.getBean(TestBean1.class);
//        System.out.println(testBean1);
//
//        TestBean2 testBean2 =(TestBean2) context.getBean("bean2");
//        TestBean2 testBean3 = context.getBean("bean2", TestBean2.class);
//        System.out.println(testBean3);
//        System.out.println(testBean2);
//
//        MyConnetion myConnetion = context.getBean(MyConnetion.class);
//        System.out.println(myConnetion);
//
//        MyConnetion myConnetion2 = context.getBean("connetion",MyConnetion.class);
//        System.out.println(myConnetion2);
//

        TestBean1 testBean1 = context.getBean(TestBean1.class);
        System.out.println(testBean1);
        TestBean1 testBean2 = context.getBean(TestBean1.class);
        System.out.println(testBean2);
        System.out.println(" ");

        TestBean2 testBean3 = context.getBean(TestBean2.class);
        System.out.println(testBean3);
        TestBean2 testBean4 = context.getBean(TestBean2.class);
        System.out.println(testBean4);
        System.out.println(" ");


        MyConnetion myConnetion = context.getBean(MyConnetion.class);
        System.out.println(myConnetion);
        MyConnetion myConnetion2 = context.getBean(MyConnetion.class);
        System.out.println(myConnetion2);
        System.out.println(" ");


        context.registerShutdownHook();


    }
}
