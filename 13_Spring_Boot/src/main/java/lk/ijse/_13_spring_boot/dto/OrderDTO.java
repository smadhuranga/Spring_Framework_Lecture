package lk.ijse._13_spring_boot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDTO {
    private int id;
    private int customerId;
    private int itemId;
    private int qty;
}
