package edu.icet.dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.entity.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrdersTm extends RecursiveTreeObject<OrdersTm> {
    private String orderId;
    private String orderDate;
    private String customerId;
    private String itemCode;
    private String itemName;
    private String repair;
    private StatusType status = StatusType.PENDING;
    private JFXButton btn;
}
