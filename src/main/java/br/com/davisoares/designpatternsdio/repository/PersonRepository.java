package br.com.davisoares.designpatternsdio.repository;

import br.com.davisoares.designpatternsdio.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByUsername(String username);
}
