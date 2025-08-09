package br.com.davisoares.designpatternsdio.security;

import br.com.davisoares.designpatternsdio.model.Person;
import br.com.davisoares.designpatternsdio.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByUsername(username);
        return UserPrincipal
                .builder()
                .userId(person.getId())
                .username(person.getUsername())
                .password(person.getPassword())
                .build();
    }
}
