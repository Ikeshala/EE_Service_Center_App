package edu.icet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Orders {
    @Id
    private String orderId;
    @Column(nullable = false)
    private String orderDate;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item itemCode;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

    private String repair;
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.PENDING;
}
