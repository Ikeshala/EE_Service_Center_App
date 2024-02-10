package edu.icet.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Customer {
    @Id
    private String customerId;
    private String customerName;
    private String customerEmail;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Customer(String customerId, String customerName, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
