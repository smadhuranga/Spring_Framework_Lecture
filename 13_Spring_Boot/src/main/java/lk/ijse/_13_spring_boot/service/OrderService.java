package lk.ijse._13_spring_boot.service;

import lk.ijse._13_spring_boot.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface OrderService {

    public boolean save(OrderDTO orderDTO) ;

    public OrderDTO getOrderById(int id);


    public boolean deleteOrder(int id) ;


    public List<OrderDTO> getAllOrders() ;

}
