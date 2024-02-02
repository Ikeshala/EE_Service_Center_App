package edu.icet.dto;

import edu.icet.entity.UserType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String email;
    private String password;
    private String type;
}

