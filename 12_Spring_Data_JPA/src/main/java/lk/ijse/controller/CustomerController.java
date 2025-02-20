package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/ui/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    List<CustomerDTO> customers = new ArrayList<>();

    @PostMapping(path = "save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customers.add(customerDTO);
        return customerDTO;
    }

    @PutMapping(path = "update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO existingCustomer = customers.get(i);
            if (existingCustomer.getId().equals(customerDTO.getId())) {
                existingCustomer.setName(customerDTO.getName());
                existingCustomer.setAddress(customerDTO.getAddress());
                existingCustomer.setAge(customerDTO.getAge());
                return existingCustomer;
            }
        }
        return customerDTO;
    }

    @GetMapping("getAll")
    public List<CustomerDTO> getAllCustomers() {
        return customers;


    }

    @DeleteMapping(path = {"delete/{id}"})
    public void deleteCustomer(@PathVariable("id") String id) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO existingCustomer = customers.get(i);
            if (existingCustomer.getId().equals(id)) {
                customers.remove(i);
                return;
            }
        }
    }


}