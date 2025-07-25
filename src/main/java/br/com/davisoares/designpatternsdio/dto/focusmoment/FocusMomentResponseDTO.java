package br.com.davisoares.designpatternsdio.dto.focusmoment;

import br.com.davisoares.designpatternsdio.model.FocusMoment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FocusMomentResponseDTO {
    private Long id;
    private String description;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    @JsonProperty("task_id")
    private Long taskId;

    public static FocusMomentResponseDTO fromModel(FocusMoment focusMoment) {
        return FocusMomentResponseDTO.builder()
                .id(focusMoment.getId())
                .description(focusMoment.getDescription())
                .startTime(focusMoment.getStartTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endTime(focusMoment.getEndTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .taskId(focusMoment.getTaskId())
                .build();
    }
}
