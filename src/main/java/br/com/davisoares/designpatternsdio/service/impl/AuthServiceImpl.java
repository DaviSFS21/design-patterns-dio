package br.com.davisoares.designpatternsdio.service.impl;

import br.com.davisoares.designpatternsdio.dto.login.LoginResponseDTO;
import br.com.davisoares.designpatternsdio.security.JwtIssuer;
import br.com.davisoares.designpatternsdio.security.UserPrincipal;
import br.com.davisoares.designpatternsdio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO attemptLogin(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getUsername(), roles);
        return LoginResponseDTO
                .builder()
                .token(token)
                .build();
    }
}
