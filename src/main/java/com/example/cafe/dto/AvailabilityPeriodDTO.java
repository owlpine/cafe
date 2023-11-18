package com.example.cafe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailabilityPeriodDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
