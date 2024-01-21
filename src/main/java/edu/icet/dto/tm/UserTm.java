package edu.icet.dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.icet.entity.UserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserTm extends RecursiveTreeObject<UserTm> {
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;
}
