package br.com.davisoares.designpatternsdio.repository;

import br.com.davisoares.designpatternsdio.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Iterable<Task> findByCompleted(boolean completed);

    Iterable<Task> findByTitleContaining(String title);
}
