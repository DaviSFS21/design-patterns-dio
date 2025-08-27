package br.com.davisoares.designpatternsdio.dto.task;

import br.com.davisoares.designpatternsdio.model.Person;
import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskRequestDTO {
    private String title;
    private String description;
    private TaskStatus status;
    @JsonProperty(value = "person_id")
    private Long personId;

    public static Task toModel(TaskRequestDTO taskRequestDTO, Person person) {
        return Task.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .status(taskRequestDTO.getStatus() == null ? TaskStatus.TODO : taskRequestDTO.getStatus())
                .person(person)
                .build();
    }
}
