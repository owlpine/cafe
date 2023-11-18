package com.example.cafe.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class AvailabilityResponse {
    private Long staffId;
    private List<AvailabilityPeriodDTO> availablePeriods = new ArrayList<>();
}