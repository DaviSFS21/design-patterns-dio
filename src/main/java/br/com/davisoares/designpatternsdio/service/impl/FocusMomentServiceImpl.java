package br.com.davisoares.designpatternsdio.service.impl;

import br.com.davisoares.designpatternsdio.model.FocusMoment;
import br.com.davisoares.designpatternsdio.model.Task;
import br.com.davisoares.designpatternsdio.repository.TaskRepository;
import br.com.davisoares.designpatternsdio.service.FocusMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.davisoares.designpatternsdio.repository.FocusMomentRepository;

import java.time.LocalDateTime;

@Service
public class FocusMomentServiceImpl implements FocusMomentService {
    @Autowired
    private FocusMomentRepository focusMomentRepository;
    @Autowired
    private TaskRepository taskRepository;

    public FocusMoment save(FocusMoment focusMoment) {
        return focusMomentRepository.save(focusMoment);
    }

    public FocusMoment findById(Long id) {
        return focusMomentRepository.findById(id).orElse(null);
    }

    public Iterable<FocusMoment> findAll() {
        return focusMomentRepository.findAll();
    }

    public void delete(Long id) {
        focusMomentRepository.deleteById(id);
    }

    public FocusMoment update(Long id,
                              LocalDateTime startTime,
                              LocalDateTime endTime,
                              String description) {
        FocusMoment focusMoment = findById(id);
        if (focusMoment != null) {
            focusMoment.setStartTime(startTime);
            focusMoment.setEndTime(endTime);
            focusMoment.setDescription(description);
            return focusMomentRepository.save(focusMoment);
        }
        return null;
    }

    public Iterable<FocusMoment> findByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        return task == null ? null : task.getFocusMoment();
    }

    public Iterable<FocusMoment> findByStartTimeBetween(LocalDateTime start, LocalDateTime end) {
        return focusMomentRepository.findByStartTimeBetween(start, end);
    }
}
