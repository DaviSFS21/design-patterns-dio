package br.com.davisoares.designpatternsdio.dto.focusmoment;

import br.com.davisoares.designpatternsdio.model.FocusMoment;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FocusMomentListResponseDTO {
    @JsonProperty("focus_moments")
    private List<FocusMomentResponseDTO> focusMoments;

    public static FocusMomentListResponseDTO fromIterable(Iterable<FocusMoment> focusMoments) {
        List<FocusMomentResponseDTO> focusMomentList = new ArrayList<>();
        focusMoments.forEach(fm ->
                focusMomentList.add(FocusMomentResponseDTO.fromModel(fm)));
        return new FocusMomentListResponseDTO(focusMomentList);
    }
}
