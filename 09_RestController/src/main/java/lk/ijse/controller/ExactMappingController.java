package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exact")
public class ExactMappingController {
    @GetMapping(path = "test1")
    public String exactMapping() {
        return "Exact Mapping";
    }
    @GetMapping(path = "test2")
    public String exactMapping2() {
        return "Exact Mapping";
    }
    @GetMapping(path = "test3")
    public String exactMapping3() {
        return "Exact Mapping (3)";
    }
}
