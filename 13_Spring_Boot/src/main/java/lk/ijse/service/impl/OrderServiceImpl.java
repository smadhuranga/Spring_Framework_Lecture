package lk.ijse.service.impl;

import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.repository.ItemRepo;
import lk.ijse.repository.OrderDetailsRepo;
import lk.ijse.repository.OrdersRepo;
import lk.ijse.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        try {
            // Validation
            if (orderDTO == null || orderDTO.getOrderId() == null ||
                orderDTO.getOrderDetails() == null || orderDTO.getOrderDetails().isEmpty()) {
                throw new RuntimeException("Invalid order data. Please check.");
            }

            if (ordersRepo.existsById(orderDTO.getOrderId())) {
                throw new RuntimeException("Order ID already exists.");
            }

            if (!checkItemsInStock(orderDTO.getOrderDetails())) {
                throw new RuntimeException("Items are out of stock.");
            }

            // Create Order
            Orders order = new Orders();
            order.setOrderId(orderDTO.getOrderId());
            order.setDateTime(orderDTO.getDateTime() != null ?
                    orderDTO.getDateTime() :
                    LocalDateTime.now().toString());
            order.setCustomerId(orderDTO.getCustomerId());

            // Create OrderDetails and update item quantities
            List<OrderDetail> orderDetails = new ArrayList<>();

            for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
                // Get item and check stock
                Item item = itemRepo.findById(detailDTO.getItemCode())
                        .orElseThrow(() -> new RuntimeException("Item not found: " + detailDTO.getItemCode()));

                // Create order detail
                OrderDetail detail = new OrderDetail();
                detail.setItemCode(detailDTO.getItemCode());
                detail.setQty(detailDTO.getQty());
                detail.setSubTotal(detailDTO.getSubTotal());
                detail.setOrder(order);
                orderDetails.add(detail);

                // Update item quantity
                item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());
                itemRepo.save(item);
            }

            order.setOrderDetails(orderDetails);
            ordersRepo.save(modelMapper.map(order, Orders.class));


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save order: " + e.getMessage());
        }
    }

    @Override
    public boolean checkItemsInStock(List<OrderDetailDTO> orderDetails) {
        for (OrderDetailDTO detail : orderDetails) {
            Item item = itemRepo.findById(detail.getItemCode()).orElse(null);

            if (item == null || item.getQtyOnHand() < detail.getQty()) {
                throw new RuntimeException("Item " + detail.getItemCode() + " is out of stock.");
            }
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(String orderId) {
        return ordersRepo.findById(orderId)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return ordersRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO mapToDTO(Orders order) {
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(this::mapToDetailDTO)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderId(),
                order.getDateTime(),
                order.getCustomerId(),
                orderDetailDTOs
        );
    }

    private OrderDetailDTO mapToDetailDTO(OrderDetail detail) {
        return new OrderDetailDTO(
                detail.getId(),
                detail.getItemCode(),
                detail.getQty(),
                detail.getSubTotal(),
                detail.getOrder().getOrderId()
        );
    }
}