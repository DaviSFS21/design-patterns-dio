package br.com.davisoares.designpatternsdio.service.impl;

import br.com.davisoares.designpatternsdio.exceptions.TaskException;
import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.repository.TaskRepository;
import br.com.davisoares.designpatternsdio.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found with id: " + id));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, String title, String description) {
        Task existingTask = findById(id);
        existingTask.setTitle(title);
        existingTask.setDescription(description);
        return taskRepository.save(existingTask);
    }

    public Task updateStatus(Long id, boolean completed) {
        Task existingTask = findById(id);
        existingTask.setCompleted(completed);
        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        Task existingTask = findById(id);
        taskRepository.delete(existingTask);
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Iterable<Task> searchTask(String title) {
        Iterable<Task> tasks = taskRepository.findByTitleContaining(title);
        List<Task> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);
        return taskList;
    }
}
