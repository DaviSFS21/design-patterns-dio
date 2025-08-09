package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.model.Person;

public interface PersonService {
    boolean registerUser(String username, String password);
    Person findById(Long id);
    Person findByUsername(String username);
}
