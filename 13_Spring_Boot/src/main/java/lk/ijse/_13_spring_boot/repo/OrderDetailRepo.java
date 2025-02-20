package lk.ijse._13_spring_boot.repo;

import lk.ijse._13_spring_boot.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
}
