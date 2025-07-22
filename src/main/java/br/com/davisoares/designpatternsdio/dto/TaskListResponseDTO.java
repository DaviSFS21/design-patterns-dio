package br.com.davisoares.designpatternsdio.dto;

import br.com.davisoares.designpatternsdio.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskListResponseDTO {
    private Iterable<Task> tasks;
}
