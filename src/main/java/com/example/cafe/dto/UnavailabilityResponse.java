package com.example.cafe.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class UnavailabilityResponse {
    private Long staffId;
    private List<AvailabilityPeriodDTO> unavailablePeriods = new ArrayList<>();
}