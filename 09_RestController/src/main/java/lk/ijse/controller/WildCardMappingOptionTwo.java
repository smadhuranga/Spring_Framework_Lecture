package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("two")
@RestController
public class WildCardMappingOptionTwo {
//   @GetMapping(path = "test/**/hello")
//    public String test() {
//        return "Hello Wild Card Mapping (2)";
//    }
}
