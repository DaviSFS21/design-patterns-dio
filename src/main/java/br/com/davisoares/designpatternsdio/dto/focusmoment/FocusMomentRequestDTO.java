package br.com.davisoares.designpatternsdio.dto.focusmoment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
