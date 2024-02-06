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
public class Item {
    @Enumerated(EnumType.STRING)
    private ItemType category;
    @Id
    private String itemCode;
    private String itemName;
    private String description;
}
