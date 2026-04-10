package com.dm3.security.Controllers;

import com.dm3.security.DTO.UserRequestDTO;
import com.dm3.security.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    public ResponseEntity createUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("admin")
    public String admin() {
        return "Acesso ADMIN";
    }
}
