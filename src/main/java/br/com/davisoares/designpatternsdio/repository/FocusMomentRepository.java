package br.com.davisoares.designpatternsdio.repository;

import br.com.davisoares.designpatternsdio.model.FocusMoment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FocusMomentRepository extends CrudRepository<FocusMoment, Long> {
    Iterable<FocusMoment> findByStartTimeBetween(LocalDateTime startTimeAfter, LocalDateTime startTimeBefore);
}
