package br.com.davisoares.designpatternsdio.service;

import br.com.davisoares.designpatternsdio.dto.focusmoment.FocusMomentRequestDTO;
import br.com.davisoares.designpatternsdio.model.FocusMoment;

import java.time.LocalDateTime;

public interface FocusMomentService {
    FocusMoment findById(Long id);
    FocusMoment save(FocusMomentRequestDTO focusMomentDTO);
    FocusMoment update(Long id,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       String description);
    void delete(Long id);
    Iterable<FocusMoment> findAll();
    Iterable<FocusMoment> findByTaskId(Long taskId);
    Iterable<FocusMoment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
