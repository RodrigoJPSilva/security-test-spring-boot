package com.dm3.security.DTO;

import lombok.*;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
}
