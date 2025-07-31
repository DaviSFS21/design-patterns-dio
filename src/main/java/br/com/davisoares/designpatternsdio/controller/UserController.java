package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.login.LoginRequestDTO;
import br.com.davisoares.designpatternsdio.dto.login.LoginResponseDTO;
import br.com.davisoares.designpatternsdio.service.AuthService;
import br.com.davisoares.designpatternsdio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserService userService;

    @PostMapping("/auth/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) {
        return authService.attemptLogin(requestDTO.getUsername(), requestDTO.getPassword());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Boolean> register(@RequestBody LoginRequestDTO requestDTO) {
        boolean status = userService.registerUser(requestDTO.getUsername(), requestDTO.getPassword());
        return ResponseEntity.ok(status);
    }
}
