package com.example.cafe.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class StaffDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime disabledAt;
    private String email;
    private String phone;
    private String password;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
}
