package edu.icet.dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrdersTm extends RecursiveTreeObject<OrdersTm> {
    private String orderId;
    private String customerId;
    private String customerName;
    private String customerEmail;
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String repair;
    private JFXButton btn;
}
