package com.example.cafe.service;

import com.example.cafe.dto.AvailabilityResponse;
import com.example.cafe.dto.AvailabilityPeriodDTO;
import com.example.cafe.dto.UnavailabilityResponse;
import com.example.cafe.mapper.AvailabilityMapper;
import com.example.cafe.model.Unavailability;
import com.example.cafe.repository.StaffRepository;
import com.example.cafe.repository.UnavailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {
    private final UnavailabilityRepository unavailabilityRepository;
    private final StaffRepository staffRepository;

    public UnavailabilityResponse createUnavailability(Long staffId, List<AvailabilityPeriodDTO> unavailabilityRequests) {
        UnavailabilityResponse unavailabilityResponse = new UnavailabilityResponse().setStaffId(staffId);
        unavailabilityRequests.forEach(unavailabilityRequest -> {
            // TODO validate unavailabilityRequest
            // TODO createdAt field + related optimisations
            Unavailability unavailability = new Unavailability()
                    .setStaffId(staffId)
                    .setStartTime(unavailabilityRequest.getStartTime())
                    .setEndTime(unavailabilityRequest.getEndTime());
            unavailability = unavailabilityRepository.save(unavailability);
            unavailabilityResponse.getUnavailablePeriods().add(AvailabilityMapper.toPeriodDTO(unavailability));
        });
        return unavailabilityResponse;
    }

    public UnavailabilityResponse getUnavailabilityByStaff(Long staffId) {
        UnavailabilityResponse unavailabilityResponse = new UnavailabilityResponse().setStaffId(staffId);
        unavailabilityRepository.findByStaffIdOrderByStartTime(staffId).forEach(unavailability ->
                unavailabilityResponse.getUnavailablePeriods().add(AvailabilityMapper.toPeriodDTO(unavailability))
        );
        return unavailabilityResponse;
    }

    public List<UnavailabilityResponse> getAllUnavailability() {
        List<UnavailabilityResponse> unavailabilityResponses = new ArrayList<>();
        staffRepository.findAll().forEach(staff -> unavailabilityResponses.add(getUnavailabilityByStaff(staff.getId())));
        return unavailabilityResponses;
    }

    public AvailabilityPeriodDTO getUnavailabilityById(Long id) {
        Unavailability unavailability = unavailabilityRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Staff not found")
        );
        return AvailabilityMapper.toPeriodDTO(unavailability);
    }

    public void deleteUnavailability(Long unavailabilityId) {
        unavailabilityRepository.deleteById(unavailabilityId);
    }

    LocalTime OPEN = LocalTime.of(9, 0);
    LocalTime CLOSE = LocalTime.of(22, 0);
    LocalDateTime availabilityStartBuffer;
    public AvailabilityResponse getAvailability(Long staffId) {
        LocalDate monday = LocalDate.now().plusDays(8 - LocalDate.now().getDayOfWeek().getValue());
        availabilityStartBuffer = LocalDateTime.of(monday, OPEN);
        AvailabilityResponse availabilityResponse = new AvailabilityResponse().setStaffId(staffId);
        unavailabilityRepository.findByStaffIdAndStartTimeGreaterThanEqualAndEndTimeLessThanOrderByStartTime(
                staffId,
                LocalDateTime.of(monday, LocalTime.of(0, 0)),
                LocalDateTime.of(monday.plusDays(7), LocalTime.of(0, 0))
        ).forEach(unavailability -> {
            int dayDelta = unavailability.getStartTime().getDayOfWeek().getValue() - availabilityStartBuffer.getDayOfWeek().getValue();
            for (; dayDelta > 0; dayDelta--) {
                if (availabilityStartBuffer.toLocalTime().isBefore(CLOSE)) {
                    availabilityResponse.getAvailablePeriods().add(new AvailabilityPeriodDTO()
                            .setStartTime(availabilityStartBuffer)
                            .setEndTime(LocalDateTime.of(availabilityStartBuffer.toLocalDate(), CLOSE))
                    );
                }
                availabilityStartBuffer = LocalDateTime.of(availabilityStartBuffer.toLocalDate().plusDays(1), OPEN);
            }
            if (unavailability.getStartTime().isAfter(availabilityStartBuffer)) {
                availabilityResponse.getAvailablePeriods().add(new AvailabilityPeriodDTO()
                        .setStartTime(availabilityStartBuffer)
                        .setEndTime(unavailability.getStartTime())
                );
            }
            availabilityStartBuffer = unavailability.getEndTime();
        });
        while (availabilityStartBuffer.getDayOfWeek() != DayOfWeek.SUNDAY) {
            availabilityResponse.getAvailablePeriods().add(new AvailabilityPeriodDTO()
                    .setStartTime(availabilityStartBuffer)
                    .setEndTime(LocalDateTime.of(availabilityStartBuffer.toLocalDate(), CLOSE))
            );
            availabilityStartBuffer = LocalDateTime.of(availabilityStartBuffer.toLocalDate().plusDays(1), OPEN);
        }
        return availabilityResponse;
    }

    public List<AvailabilityResponse> getAllAvailability() {
        List<AvailabilityResponse> availabilityResponses = new ArrayList<>();
        staffRepository.findAll().forEach(staff -> availabilityResponses.add(getAvailability(staff.getId())));
        return availabilityResponses;
    }
}
