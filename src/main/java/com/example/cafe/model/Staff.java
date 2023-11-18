package com.example.cafe.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("staff")
@Data
@Accessors(chain = true)
public class Staff {
    @Id
    private Long id;

    // why ZonedDateTime doesn't work?
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime disabledAt;
    private String email;
    private String phone;
    private String encryptedPassword;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
}
