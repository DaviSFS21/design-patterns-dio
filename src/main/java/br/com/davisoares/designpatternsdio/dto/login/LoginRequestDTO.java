package br.com.davisoares.designpatternsdio.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDTO {
    private String username;
    private String password;
}
