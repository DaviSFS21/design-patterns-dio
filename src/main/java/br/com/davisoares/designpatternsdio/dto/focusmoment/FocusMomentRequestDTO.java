package br.com.davisoares.designpatternsdio.dto.focusmoment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FocusMomentRequestDTO {
    private String description;
    @JsonProperty("start_time")
    private LocalDateTime startTime;
    @JsonProperty("end_time")
    private LocalDateTime endTime;
    @JsonProperty
    private Long taskId;
}
