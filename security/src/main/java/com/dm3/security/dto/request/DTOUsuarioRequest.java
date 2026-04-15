package com.dm3.security.dto.request;


import com.dm3.security.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DTOUsuarioRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido") // Validação mágica do Spring!
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    private List<Role> roles;

    // Gere os Getters e Setters!
}