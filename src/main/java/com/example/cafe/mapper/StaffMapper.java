package com.example.cafe.mapper;

import com.example.cafe.dto.StaffDTO;
import com.example.cafe.model.Staff;

public class StaffMapper {
    public static StaffDTO toDTO(Staff staff) {
        return new StaffDTO()
                .setId(staff.getId())
                .setCreatedAt(staff.getCreatedAt())
                .setUpdatedAt(staff.getUpdatedAt())
                .setDisabledAt(staff.getDisabledAt())
                .setEmail(staff.getEmail())
                .setPhone(staff.getPhone())
                .setIsAdmin(staff.getIsAdmin())
                .setFirstName(staff.getFirstName())
                .setLastName(staff.getLastName());
    }

    public static Staff toModel(StaffDTO staffDTO, Staff staff) {
        return staff
                .setEmail(staffDTO.getEmail())
                .setPhone(staffDTO.getPhone())
                .setIsAdmin(staffDTO.getIsAdmin())
                .setFirstName(staffDTO.getFirstName())
                .setLastName(staffDTO.getLastName());
    }

    //
}
