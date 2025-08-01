package br.com.davisoares.designpatternsdio.security;

import br.com.davisoares.designpatternsdio.model.User;
import br.com.davisoares.designpatternsdio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return UserPrincipal
                .builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
