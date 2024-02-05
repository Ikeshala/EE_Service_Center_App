package edu.icet.dto;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private JFXButton btn;

    public CustomerDto(String customerId, String customerName, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
