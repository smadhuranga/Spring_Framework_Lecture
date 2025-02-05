package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("one")
@RestController
public class WildCardMappingOptionOne {
    @GetMapping(path = "test/*/hello")
    public String test() {
        return "Hello Wild Card Mapping (1)";
    }
    @GetMapping(path = "test/*/*")
    public String test2() {
        return "Hello Wild Card Mapping (2)";
    }
}
