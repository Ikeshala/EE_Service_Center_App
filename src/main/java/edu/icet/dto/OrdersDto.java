package edu.icet.dto;

import com.jfoenix.controls.JFXButton;
import edu.icet.entity.ItemType;
import edu.icet.entity.StatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDto {
    private String orderId;
    private String orderDate;
    private String customerId;
    private String itemCode;
    private String itemName;
    private String repair;
    private StatusType status = StatusType.PENDING;
    private JFXButton btn;

    public OrdersDto(String orderId, String orderDate, String customerId, String itemCode, String itemName, String repair, StatusType status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.repair = repair;
        this.status = status;
    }
}
