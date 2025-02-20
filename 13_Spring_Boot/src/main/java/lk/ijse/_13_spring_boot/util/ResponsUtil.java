package lk.ijse._13_spring_boot.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResponsUtil {

    private int code;
    private String message;
    private Object data;


}
