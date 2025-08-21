package br.com.davisoares.designpatternsdio.repository;

import br.com.davisoares.designpatternsdio.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Iterable<Task> findAllByPersonId(Long id);

    Iterable<Task> findByTitleContaining(String title);
}
