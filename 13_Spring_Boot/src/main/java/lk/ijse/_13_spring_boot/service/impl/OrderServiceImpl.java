package lk.ijse._13_spring_boot.service.impl;

import lk.ijse._13_spring_boot.dto.OrderDTO;
import lk.ijse._13_spring_boot.entity.Orders;
import lk.ijse._13_spring_boot.repo.OrderRepo;
import lk.ijse._13_spring_boot.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean save(OrderDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);
        orderRepo.save(order);
        return true;
    }

    @Override
    public OrderDTO getOrderById(int id) {
        Optional<Orders> orderOptional = orderRepo.findById(id);
        return orderOptional.map(order -> modelMapper.map(order, OrderDTO.class)).orElse(null);
    }

    @Override
    public boolean deleteOrder(int id) {
        if (orderRepo.existsById(id)) {
            orderRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepo.findAll();
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {}.getType());
    }
}