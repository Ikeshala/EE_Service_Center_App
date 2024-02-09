package edu.icet.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Orders {
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "itemCode")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private String repair;
}
