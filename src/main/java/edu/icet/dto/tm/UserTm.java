package edu.icet.dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.entity.UserType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserTm extends RecursiveTreeObject<UserTm> {
    private String userId;
    private String email;
    private String password;
    private UserType type;
    private JFXButton btn;
}
