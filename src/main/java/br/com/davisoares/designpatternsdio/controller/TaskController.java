package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.TaskListResponseDTO;
import br.com.davisoares.designpatternsdio.dto.TaskRequestDTO;
import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.com.davisoares.designpatternsdio.utils.Utils.ConvertToModel;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskListResponseDTO> getAllTasks() {
        TaskListResponseDTO tasks = new TaskListResponseDTO(taskService.findAll());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestDTO task) {
        Task newTask = ConvertToModel(task);
        Task createdTask = taskService.save(newTask);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestParam String title,
                                           @RequestParam String description) {
        Task updatedTask = taskService.update(id, title, description);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id,
                                                 @RequestParam boolean completed) {
        Task updatedTask = taskService.updateStatus(id, completed);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/search")
    public ResponseEntity<TaskListResponseDTO> searchTasks(@RequestParam String title) {
        TaskListResponseDTO tasks = new TaskListResponseDTO(taskService.searchTask(title));
        return ResponseEntity.ok(tasks);
    }
}
