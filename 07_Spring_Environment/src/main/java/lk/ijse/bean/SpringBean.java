package lk.ijse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {
    @Value("${os.name}")
    private String osName;
    @Value("${LOGNAME}")
    private String logName;
    @Value("${user.home}")
    private String userHome;
    @Value("${database.username}")
    private String userName;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(osName);
        System.out.println(logName);
        System.out.println(userHome);
        System.out.println(userName);
    }
}
