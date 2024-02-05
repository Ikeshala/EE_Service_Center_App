package edu.icet.dto.tm;

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
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private ItemType category;
    private String itemCode;
    private String itemName;
    private String description;
    private int quantity;
}
