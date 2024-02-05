package edu.icet.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
    @Id
    private String userId;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType type;
}