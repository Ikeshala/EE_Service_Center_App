package edu.icet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Item {
    @Enumerated(EnumType.STRING)
    private ItemType category;
    @Id
    private String itemCode;
    private String itemName;
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Item(ItemType category, String itemCode, String itemName, String description) {
        this.category = category;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
    }
}
