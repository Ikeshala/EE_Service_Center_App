package edu.icet.dto;

import com.jfoenix.controls.JFXButton;
import edu.icet.entity.UserType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String email;
    private String password;
    private UserType type;
    private JFXButton btn;
    public UserDto(String userId, String email, String password, UserType type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}