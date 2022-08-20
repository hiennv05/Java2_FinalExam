package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.dto.request.DepartmentRequest;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface IDepartmentService {
    Page<Department> getAllDepartments(Pageable pageable, String search, DepartmentFilterForm filterForm);

    Optional<Department> getDepartmentById(Integer id);

    Department createDepartment(DepartmentRequest request);

}
