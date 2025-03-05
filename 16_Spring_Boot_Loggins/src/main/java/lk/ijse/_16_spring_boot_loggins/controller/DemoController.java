package lk.ijse._16_spring_boot_loggins.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {
    //Trace,Debug,info,warn,error

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/log-deom")
    public String logDemo(){
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        return "Log Demo";
    }

}
