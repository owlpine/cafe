package com.example.cafe.repository;

import com.example.cafe.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
//    @Override
//    List<Staff> findAll();
    // use MappedCollection to pull in unavailabilities
}
