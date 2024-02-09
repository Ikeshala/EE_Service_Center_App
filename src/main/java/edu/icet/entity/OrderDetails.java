package edu.icet.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class OrderDetails {
    @Id
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    @Enumerated(EnumType.STRING)
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String repair;
}
