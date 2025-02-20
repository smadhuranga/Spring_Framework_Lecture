package lk.ijse._13_spring_boot.repo;


import lk.ijse._13_spring_boot.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Integer> {
}
