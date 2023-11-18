package com.example.cafe.mapper;

import com.example.cafe.dto.AvailabilityPeriodDTO;
import com.example.cafe.model.Unavailability;

public class AvailabilityMapper {
    public static AvailabilityPeriodDTO toPeriodDTO(Unavailability unavailability) {
        return new AvailabilityPeriodDTO()
                .setId(unavailability.getId())
                .setStartTime(unavailability.getStartTime())
                .setEndTime(unavailability.getEndTime());
    }
}
