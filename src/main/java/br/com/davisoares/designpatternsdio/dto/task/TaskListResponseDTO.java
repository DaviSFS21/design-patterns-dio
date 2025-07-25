package br.com.davisoares.designpatternsdio.dto.task;

import br.com.davisoares.designpatternsdio.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskListResponseDTO {
    private List<TaskResponseDTO> tasks;

    public static TaskListResponseDTO fromIterable(Iterable<Task> tasks) {
        List<TaskResponseDTO> taskList = new ArrayList<>();
        tasks.forEach(t -> taskList.add(TaskResponseDTO.fromModel(t)));
        return new TaskListResponseDTO(taskList);
    }
}
