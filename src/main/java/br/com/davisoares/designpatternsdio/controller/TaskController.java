package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.task.TaskListResponseDTO;
import br.com.davisoares.designpatternsdio.dto.task.TaskRequestDTO;
import br.com.davisoares.designpatternsdio.dto.task.TaskResponseDTO;
import br.com.davisoares.designpatternsdio.model.Person;
import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.security.UserPrincipal;
import br.com.davisoares.designpatternsdio.service.PersonService;
import br.com.davisoares.designpatternsdio.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Autowired
    private PersonService personService;

    @GetMapping
    @Operation(summary = "Retrieve all tasks",
               description = "Returns a list of all tasks stored in the system.")
    public ResponseEntity<TaskListResponseDTO> getAllTasks(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Iterable<Task> tasks = taskService.findAllByPersonId(userPrincipal.getUserId());
        return ResponseEntity.ok(TaskListResponseDTO.fromIterable(tasks));
    }

    @PostMapping
    @Operation(summary = "Create a new task",
               description = "Creates a new task with the provided details.")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskDTO) {
        Person person = personService.findById(taskDTO.getPersonId());
        Task newTask = TaskRequestDTO.toModel(taskDTO, person);
        Task task = taskService.save(newTask);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(task));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task",
               description = "Updates the details of an existing task identified by its ID.")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
                                                      @RequestParam String title,
                                                      @RequestParam String description) {
        Task updatedTask = taskService.update(id, title, description);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(updatedTask));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task",
               description = "Deletes an existing task identified by its ID.")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update task status",
               description = "Updates the completion status of a task identified by its ID.")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@PathVariable Long id,
                                                            @RequestParam boolean completed) {
        Task updatedTask = taskService.updateStatus(id, completed);
        return ResponseEntity.ok(TaskResponseDTO.fromModel(updatedTask));
    }

    @GetMapping("/search")
    @Operation(summary = "Search tasks by title",
               description = "Returns a list of tasks that match the provided title.")
    public ResponseEntity<TaskListResponseDTO> searchTasks(@RequestParam String title) {
        TaskListResponseDTO tasks = TaskListResponseDTO.fromIterable(taskService.searchTask(title));
        return ResponseEntity.ok(tasks);
    }
}
