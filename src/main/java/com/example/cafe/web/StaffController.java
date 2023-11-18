package com.example.cafe.web;

import com.example.cafe.dto.StaffDTO;
import com.example.cafe.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("{id}")
    public StaffDTO getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @PostMapping
    public StaffDTO createStaff(@RequestBody StaffDTO staffDTO) {
        return staffService.createStaff(staffDTO);
    }

    // use put to disable + column of staff status
    @PutMapping("{id}")
    public StaffDTO updateStaff(@PathVariable Long id, @RequestBody StaffDTO staffDTO) {
        return staffService.updateStaff(id, staffDTO);
    }
}
