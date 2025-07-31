package br.com.davisoares.designpatternsdio.controller;

import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentListResponseDTO;
import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentRequestDTO;
import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentResponseDTO;
import br.com.davisoares.designpatternsdio.model.FocusMoment;
import br.com.davisoares.designpatternsdio.service.FocusMomentService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Autowired
    private FocusMomentService focusMomentService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping
    @Operation(summary = "Retrieve all focus moments", description = "Returns a list of all focus moments stored in the system.")
    public ResponseEntity<FocusMomentListResponseDTO> getAllFocusMoments() {
        Iterable<FocusMoment> focusMoments = focusMomentService.findAll();
        return ResponseEntity.ok(FocusMomentListResponseDTO.fromIterable(focusMoments));
    }

    @PostMapping
    @Operation(summary = "Create a new focus moment", description = "Creates a new focus moment with the provided details.")
    public ResponseEntity<FocusMomentResponseDTO> createFocusMoment(@RequestBody FocusMomentRequestDTO focusMomentDTO) {
        FocusMoment newFocusMoment = focusMomentService.save(focusMomentDTO);
        return ResponseEntity.ok(FocusMomentResponseDTO.fromModel(newFocusMoment));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing focus moment",
            description = "Updates the details of an existing focus moment identified by its ID.")
    public ResponseEntity<FocusMomentResponseDTO> updateFocusMoment(
            @PathVariable Long id,
            @RequestParam String description,
            @RequestParam("start_time") String startTime,
            @RequestParam("end_time") String endTime) {
        LocalDateTime parsedStartTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime parsedEndTime = LocalDateTime.parse(endTime, formatter);
        FocusMoment updatedFocusMoment = focusMomentService.update(id, parsedStartTime, parsedEndTime, description);
        return ResponseEntity.ok(FocusMomentResponseDTO.fromModel(updatedFocusMoment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a focus moment", description = "Deletes a focus moment identified by its ID.")
    public ResponseEntity<Void> deleteFocusMoment(@PathVariable Long id) {
        focusMomentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("task/{taskId}")
    @Operation(summary = "Retrieve focus moments by task ID",
            description = "Returns a list of focus moments associated with a specific task identified by its ID.")
    public ResponseEntity<FocusMomentListResponseDTO> getFocusMomentsByTaskId(@PathVariable Long taskId) {
        Iterable<FocusMoment> focusMoments = focusMomentService.findByTaskId(taskId);
        return ResponseEntity.ok(FocusMomentListResponseDTO.fromIterable(focusMoments));
    }

    @GetMapping("/search")
    @Operation(summary = "Search focus moments by start time",
            description = "Returns a list of focus moments that started within the specified time range.")
    public ResponseEntity<FocusMomentListResponseDTO> searchFocusMomentsByStartTime(
            @RequestParam("start_time") String startTime,
            @RequestParam("end_time") String endTime) {
        LocalDateTime parsedStartTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime parsedEndTime = LocalDateTime.parse(endTime, formatter);
        Iterable<FocusMoment> focusMoments = focusMomentService.findByStartTimeBetween(parsedStartTime, parsedEndTime);
        return ResponseEntity.ok(FocusMomentListResponseDTO.fromIterable(focusMoments));
    }
}
