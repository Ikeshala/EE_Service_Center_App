package edu.icet.dto;

import com.jfoenix.controls.JFXButton;
import edu.icet.entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailsDto {
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String repair;
    private JFXButton btn;

    public OrderDetailsDto(String orderId, String customerId, String customerName, String customerEmail, ItemType category, String itemCode, String itemName, String repair) {
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
