package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.model.TaskStatus;

public interface TaskService {
    Task findById(Long id);
    Task save(Task task);
    Task update(Long id, String title, String description);
    Task updateStatus(Long id, TaskStatus status);
    void delete(Long id);
    Iterable<Task> findAllByPersonId(Long userId);
    Iterable<Task> searchTask(String title);
}
