package lk.ijse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean,DisposableBean{

    @Override
    public void destroy() throws Exception {
        System.out.println("My Connection Destroyed");
    }

    public MyConnection() {
        System.out.println("MyConnection Created");
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("My Connection Bean Name set");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("My Connection Bean Factory set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("My Connection Initialize with Application Context");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("My Connection After Properties Set");
    }


}
