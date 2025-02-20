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
           return new ResponsUtil(404, "Customer Not Saved", null);

    }

    @GetMapping("search/{id}")
    public ResponsUtil getCustomerById(@PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);

      if (customerDTO != null) {
          return new ResponsUtil(200, "Success", customerDTO);
      } else {
          return new ResponsUtil(404, "Customer Not Found", null);
      }
    }

    @PutMapping("update")
    public ResponsUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.updateCustomer(customerDTO);

     if (res) {
         return new ResponsUtil(200, "Customer Updated", null);
     } else {
         return new ResponsUtil(404, "Customer Not Updated", null);
     }
    }

    @DeleteMapping("delete/{id}")
    public ResponsUtil deleteCustomer(@PathVariable int id) {
        boolean res = customerService.deleteCustomer(id);

        if (res) {
            return new ResponsUtil(200, "Customer Deleted", null);
        } else {
            return new ResponsUtil(404, "Customer Not Deleted", null);
        }
    }

    @GetMapping("getAll")
    public ResponsUtil getAllCustomers() {
       return new ResponsUtil(200, "Success", customerService.getAllCustomers());
    }
}
