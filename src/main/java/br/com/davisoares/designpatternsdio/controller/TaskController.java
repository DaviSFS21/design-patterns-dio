package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.task.TaskListResponseDTO;
import br.com.davisoares.designpatternsdio.dto.task.TaskRequestDTO;
import br.com.davisoares.designpatternsdio.dto.task.TaskResponseDTO;
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

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<TaskListResponseDTO> getAllTasks() {
        Iterable<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(TaskListResponseDTO.fromIterable(tasks));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskDTO) {
        Task newTask = TaskRequestDTO.toModel(taskDTO);
        Task task = taskService.save(newTask);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
                                                      @RequestParam String title,
                                                      @RequestParam String description) {
        Task updatedTask = taskService.update(id, title, description);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@PathVariable Long id,
                                                            @RequestParam boolean completed) {
        Task updatedTask = taskService.updateStatus(id, completed);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(updatedTask));
    }

    @GetMapping("/search")
    public ResponseEntity<TaskListResponseDTO> searchTasks(@RequestParam String title) {
        TaskListResponseDTO tasks = TaskListResponseDTO.fromIterable(taskService.searchTask(title));
        return ResponseEntity.ok(tasks);
    }
}
