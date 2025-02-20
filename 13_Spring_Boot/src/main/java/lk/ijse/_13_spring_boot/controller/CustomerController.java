package lk.ijse._13_spring_boot.controller;



import lk.ijse._13_spring_boot.dto.CustomerDTO;
import lk.ijse._13_spring_boot.service.impl.CustomerServiceImpl;
import lk.ijse._13_spring_boot.util.ResponsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping(path = "save")
    public ResponsUtil saveCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.save(customerDTO);
       if (res) {
           return new ResponsUtil(201, "Customer Saved", null);
       }
           return new ResponsUtil(400, "Customer Not Saved", null);

    }

    @GetMapping("search/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);

        if (customerDTO != null) {
            return customerDTO;
        } else {
            return null;
        }
    }

    @PutMapping("update")
    public ResponsUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.updateCustomer(customerDTO);

     if (res) {
         return new ResponsUtil(200, "Customer Updated", null);
     } else {
         return new ResponsUtil(400, "Customer Not Updated", null);
     }
    }

    @DeleteMapping("delete/{id}")
    public Boolean deleteCustomer(@PathVariable int id) {
        boolean res = customerService.deleteCustomer(id);
        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("getAll")
    public ResponsUtil getAllCustomers() {
       return new ResponsUtil(200, "Success", customerService.getAllCustomers());
    }
}
