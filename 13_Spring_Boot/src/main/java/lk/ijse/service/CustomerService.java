package lk.ijse.service;

import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);

    CustomerDTO getCustomerById(String id);

    List<CustomerDTO> getAllCustomers();

}
