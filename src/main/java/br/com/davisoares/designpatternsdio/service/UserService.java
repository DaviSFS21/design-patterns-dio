package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.dto.login.LoginResponseDTO;
import br.com.davisoares.designpatternsdio.model.User;

public interface UserService {
    boolean registerUser(String username, String password);
    User findByUsername(String username);
}
