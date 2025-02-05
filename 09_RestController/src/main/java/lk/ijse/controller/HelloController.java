package lk.ijse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello() {

        return "Hello Get";
    }
    @PostMapping
    public String post(){
        return "Hello Post";
    }
    @PutMapping
    public String put(){
        return "Hello Put";
    }
    @DeleteMapping
    public String delete(){
        return "Hello Delete";
    }
    @PatchMapping
    public String patch(){
        return "Hello Patch";
    }

}
