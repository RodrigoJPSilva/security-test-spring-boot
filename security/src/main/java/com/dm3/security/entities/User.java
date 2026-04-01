package com.dm3.security.entities;

import com.dm3.security.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
