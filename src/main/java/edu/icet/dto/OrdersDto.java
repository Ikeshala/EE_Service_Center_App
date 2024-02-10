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
    private LocalDate orderDate;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String repair;
    private StatusType status = StatusType.PENDING;
    private JFXButton btn;
}
