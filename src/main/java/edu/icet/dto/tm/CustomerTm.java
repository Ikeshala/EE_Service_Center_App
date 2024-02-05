package edu.icet.dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerTm extends RecursiveTreeObject<CustomerTm> {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private JFXButton btn;
}
