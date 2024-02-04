package edu.icet.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    private String userId;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(String userId, String email, String password, UserType type) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}