package lk.ijse._13_spring_boot.controller;

import lk.ijse._13_spring_boot.dto.OrderDTO;
import lk.ijse._13_spring_boot.service.OrderService;
import lk.ijse._13_spring_boot.util.ResponsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping(path = "save")
    public ResponsUtil saveOrder(@RequestBody OrderDTO orderDTO) {
        boolean res = orderService.save(orderDTO);

        if (res) {
            return new ResponsUtil(200, "Order is saved!", null);
        }
        return new ResponsUtil(409, "Already exist!", null);
    }

    @GetMapping("search/{id}")
    public ResponsUtil getOrderById(@PathVariable int id) {
        OrderDTO orderDTO = orderService.getOrderById(id);

        if (orderDTO != null) {
            return new ResponsUtil(200, "Order is found!", orderDTO);
        } else {
            return new ResponsUtil(404, "Order not found!", null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponsUtil deleteOrder(@PathVariable int id) {
        boolean res = orderService.deleteOrder(id);

        if (res) {
            return new ResponsUtil(200, "Order is deleted!", null);
        }
        return new ResponsUtil(404, "Order not found!", null);
    }

    @GetMapping("getAll")
    public ResponsUtil getAllOrders() {
        if (orderService.getAllOrders() != null) {
            return new ResponsUtil(200, "Success", orderService.getAllOrders());
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }
}