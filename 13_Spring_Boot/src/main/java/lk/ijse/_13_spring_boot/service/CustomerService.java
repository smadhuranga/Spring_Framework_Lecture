package lk.ijse._13_spring_boot.service;

import lk.ijse._13_spring_boot.dto.CustomerDTO;
import lk.ijse._13_spring_boot.entity.Customer;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public boolean save(CustomerDTO customerDTO);

    public CustomerDTO getCustomerById(int id);

    public boolean updateCustomer(CustomerDTO customerDTO);

    public boolean deleteCustomer(int id);

    public List<CustomerDTO> getAllCustomers();
}
