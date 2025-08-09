package br.com.davisoares.designpatternsdio.service.impl;

import br.com.davisoares.designpatternsdio.model.Person;
import br.com.davisoares.designpatternsdio.repository.PersonRepository;
import br.com.davisoares.designpatternsdio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    public boolean registerUser(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        personRepository.save(Person.builder()
                .username(username)
                .password(encodedPassword)
                .build());

        return true;
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }
}
