package com.example.cafe.repository;

import com.example.cafe.model.Unavailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UnavailabilityRepository extends CrudRepository<Unavailability, Long> {
    Iterable<Unavailability> findByStaffIdAndStartTimeGreaterThanEqualAndEndTimeLessThanOrderByStartTime(
            Long staffId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    Iterable<Unavailability> findByStaffIdOrderByStartTime(Long staffId);
}