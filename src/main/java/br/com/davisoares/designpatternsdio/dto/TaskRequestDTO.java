package br.com.davisoares.designpatternsdio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskRequestDTO {
    private String title;
    private String description;
    private boolean completed;
}
