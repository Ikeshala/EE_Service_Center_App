package edu.icet.dto;

import com.jfoenix.controls.JFXButton;
import edu.icet.entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDto {
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String repair;
    private JFXButton btn;

    public OrdersDto(String orderId, String customerId, String customerName, String customerEmail, ItemType category, String itemCode, String itemName, String repair) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.category = category;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.repair = repair;
    }
}
