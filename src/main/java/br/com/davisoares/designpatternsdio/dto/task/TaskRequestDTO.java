package br.com.davisoares.designpatternsdio.dto.task;

import br.com.davisoares.designpatternsdio.model.Task;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskRequestDTO {
    private String title;
    private String description;
    private boolean completed;

    public static Task toModel(TaskRequestDTO taskRequestDTO) {
        return Task.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .completed(taskRequestDTO.isCompleted())
                .build();
    }
}
