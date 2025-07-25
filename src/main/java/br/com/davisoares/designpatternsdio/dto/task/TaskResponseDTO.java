package br.com.davisoares.designpatternsdio.dto.task;

import br.com.davisoares.designpatternsdio.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public static TaskResponseDTO fromModel(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }
}
