package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IDepartmentService {
    Page<Department> getAllDepartments(Pageable pageable);

    Optional<Department> getDepartmentById(Integer id);
}
