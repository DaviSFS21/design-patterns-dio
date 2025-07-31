package br.com.davisoares.designpatternsdio.service.impl;

import br.com.davisoares.designpatternsdio.dto.login.LoginResponseDTO;
import br.com.davisoares.designpatternsdio.model.User;
import br.com.davisoares.designpatternsdio.repository.UserRepository;
import br.com.davisoares.designpatternsdio.security.JwtIssuer;
import br.com.davisoares.designpatternsdio.security.UserPrincipal;
import br.com.davisoares.designpatternsdio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String username, String password) {
        userRepository.save(User.builder()
                .username(username)
                .password(password)
                .build());

        return true;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
