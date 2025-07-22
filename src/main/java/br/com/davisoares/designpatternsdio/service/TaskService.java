package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.model.Task;
import org.springframework.stereotype.Service;

public interface TaskService {
    Task findById(Long id);
    Task save(Task task);
    Task update(Long id, String title, String description);
    Task updateStatus(Long id, boolean status);
    void delete(Long id);
    Iterable<Task> findAll();
    Iterable<Task> searchTask(String title);
}
