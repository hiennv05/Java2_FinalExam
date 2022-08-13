package com.vti.rw41.FinalExam.service;


import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    IDepartmentRepository repository;

    @Override
    public Page<Department> getAllDepartments(Pageable pageable, String search) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        return Optional.of(repository.getById(id));
    }
}
