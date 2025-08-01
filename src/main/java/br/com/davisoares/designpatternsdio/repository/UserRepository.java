package br.com.davisoares.designpatternsdio.repository;

import br.com.davisoares.designpatternsdio.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
