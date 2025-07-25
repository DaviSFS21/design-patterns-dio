package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentListResponseDTO;
import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentRequestDTO;
import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentResponseDTO;
import br.com.davisoares.designpatternsdio.model.FocusMoment;
import br.com.davisoares.designpatternsdio.service.FocusMomentService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/focus-moments")
public class FocusMomentController {
    // TODO: finish endpoints
    @Autowired
    private FocusMomentService focusMomentService;

    @GetMapping
    public ResponseEntity<FocusMomentListResponseDTO> getAllFocusMoments() {
        Iterable<FocusMoment> focusMoments = focusMomentService.findAll();
        return ResponseEntity.ok(FocusMomentListResponseDTO.fromIterable(focusMoments));
    }

    @PostMapping
    public ResponseEntity<FocusMomentResponseDTO> createFocusMoment(@RequestBody FocusMomentRequestDTO focusMomentDTO) {
        // TODO: assign task to focusMoment before saving
        FocusMoment focusMoment = FocusMomentRequestDTO.toModel(focusMomentDTO);
        FocusMoment newFocusMoment = focusMomentService.save(focusMoment);
        return ResponseEntity.ok(FocusMomentResponseDTO.fromModel(newFocusMoment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FocusMomentResponseDTO> updateFocusMoment(@PathVariable Long id,
                                                                    @RequestParam String description,
                                                                    @RequestParam("start_time") String startTime,
                                                                    @RequestParam("end_time") String endTime,
                                                                    @RequestParam(required = false) Long taskId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedStartTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime parsedEndTime = LocalDateTime.parse(endTime, formatter);
        FocusMoment updatedFocusMoment = focusMomentService.update(id, parsedStartTime, parsedEndTime, description);
        return ResponseEntity.ok(FocusMomentResponseDTO.fromModel(updatedFocusMoment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFocusMoment(@PathVariable Long id) {
        focusMomentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("task/{taskId}")
    public ResponseEntity<FocusMomentListResponseDTO> getFocusMomentsByTaskId(@PathVariable Long taskId) {
        Iterable<FocusMoment> focusMoments = focusMomentService.findByTaskId(taskId);
        return ResponseEntity.ok(FocusMomentListResponseDTO.fromIterable(focusMoments));
    }
}
