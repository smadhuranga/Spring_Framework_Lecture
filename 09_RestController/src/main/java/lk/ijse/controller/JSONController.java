package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;

@RestController
@RequestMapping("json")
public class JSONController {
    @PostMapping
    public String test1(@RequestBody CustomerDTO customerDTO) {
        return customerDTO.getFirstName();
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerDTO test2(){
        return new CustomerDTO("A", "B", 10);
    }
    @GetMapping(path = "test3", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<CustomerDTO> test3(){
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(new CustomerDTO("A", "B", 10));
        customerDTOS.add(new CustomerDTO("C", "D", 20));
        customerDTOS.add(new CustomerDTO("E", "F", 30));
        return customerDTOS;
    }
}
