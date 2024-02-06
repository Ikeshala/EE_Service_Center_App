package edu.icet.dto;

import edu.icet.entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDto {
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String description;
}
