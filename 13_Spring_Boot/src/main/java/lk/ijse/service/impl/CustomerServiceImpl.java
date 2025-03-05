package lk.ijse.service.impl;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper; // ModelMapper

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
//        Customer customer = new Customer(
//                customerDTO.getId(),
//                customerDTO.getName(),
//                customerDTO.getAddress());
        if (customerRepo.existsById(customerDTO.getId())) {
            throw new RuntimeException("Customer already exists..!");
        }

        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress());
        }
        return null;
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
//            Customer customer = new Customer(
//                    customerDTO.getId(),
//                    customerDTO.getName(),
//                    customerDTO.getAddress());
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        }

        throw new RuntimeException("No such customer for update..!");
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        }

        throw new RuntimeException("No such customer for delete..!");
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
//        List<Customer> customers = customerRepo.findAll();
//        List<CustomerDTO> customerDTOs = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            customerDTOs.add(new CustomerDTO(
//                    customer.getId(),
//                    customer.getName(),
//                    customer.getAddress()));
//        }

        return modelMapper.map(customerRepo.findAll(),
                new TypeToken<List<CustomerDTO>>() {}.getType());
    }
}
