package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.dto.login.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO attemptLogin(String username, String password);
}
