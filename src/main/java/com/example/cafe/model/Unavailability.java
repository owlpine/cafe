package com.example.cafe.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("unavailability")
@Data
@Accessors(chain = true)
public class Unavailability {
    @Id
    private Long id;

    private Long staffId;
    private LocalDateTime createdAt;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
