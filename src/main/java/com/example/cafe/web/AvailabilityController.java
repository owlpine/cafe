package com.example.cafe.web;

import com.example.cafe.dto.AvailabilityResponse;
import com.example.cafe.dto.AvailabilityPeriodDTO;
import com.example.cafe.dto.UnavailabilityResponse;
import com.example.cafe.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("availability")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @PostMapping("unavailable-period")
    public UnavailabilityResponse createUnavailability(
            @RequestParam(name = "staff") Long staffId,
            @RequestBody List<AvailabilityPeriodDTO> unavailabilityRequests
    ) {
        return availabilityService.createUnavailability(staffId, unavailabilityRequests);
    }

    @GetMapping("unavailable-period")
    public List<UnavailabilityResponse> getUnavailability(
            @RequestParam(name = "staff", required = false) Long staffId
    ) {
        return staffId != null ?
                List.of(availabilityService.getUnavailabilityByStaff(staffId)) : availabilityService.getAllUnavailability();
    }

    @GetMapping("unavailable-period/{id}")
    public AvailabilityPeriodDTO getUnavailabilityById(@PathVariable Long id) {
        return availabilityService.getUnavailabilityById(id);
    }

    @DeleteMapping("unavailable-period/{id}")
    public void deleteUnavailability(@PathVariable Long id) {
        availabilityService.deleteUnavailability(id);
    }

    @GetMapping
    public List<AvailabilityResponse> getAvailability(
            @RequestParam(name = "staff", required = false) Long staffId
    ) {
        return staffId != null ?
                List.of(availabilityService.getAvailability(staffId)) : availabilityService.getAllAvailability();
    }
}

