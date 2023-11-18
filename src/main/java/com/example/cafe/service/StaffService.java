package com.example.cafe.service;

import com.example.cafe.dto.StaffDTO;
import com.example.cafe.mapper.StaffMapper;
import com.example.cafe.model.Staff;
import com.example.cafe.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public List<StaffDTO> getAllStaff() {
        List<StaffDTO> staffDTOs = new ArrayList<>();
        staffRepository.findAll().forEach(
                staff -> staffDTOs.add(StaffMapper.toDTO(staff))
        );
        return staffDTOs;
    }

    public StaffDTO getStaffById(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Staff not found")
        );
        return StaffMapper.toDTO(staff);
    }

    public StaffDTO createStaff(StaffDTO staffDTO) {
        Staff staff = new Staff().setCreatedAt(LocalDateTime.now());
        staff = staffRepository.save(StaffMapper.toModel(staffDTO, staff));
        return StaffMapper.toDTO(staff);
    }

    public StaffDTO updateStaff(Long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Staff not found")
        );
        staff.setUpdatedAt(LocalDateTime.now());
        staff = staffRepository.save(StaffMapper.toModel(staffDTO, staff));
        return StaffMapper.toDTO(staff);
    }

    public StaffDTO disableStaff(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Staff not found")
        );
        staff.setDisabledAt(LocalDateTime.now());
        staff = staffRepository.save(staff);
        return StaffMapper.toDTO(staff);
    }
}
