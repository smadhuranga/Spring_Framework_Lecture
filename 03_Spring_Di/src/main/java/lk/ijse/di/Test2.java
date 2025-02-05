package lk.ijse.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 implements DiInterface {
    //    @Autowired
    Di test1;

    //    @Autowired
//    public Test2(Di test1) {
//        this.test1 = test1;
//
//    }
//    @Autowired
//    public void setter(Di test1) {
//        this.test1 = test1;
//    }

    public void display() {
        test1.sayHello();

    }

    @Autowired
    @Override
    public void inject(Di test1) {

        this.test1 = test1;
    }
}
