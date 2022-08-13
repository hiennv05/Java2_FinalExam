package com.vti.rw41.FinalExam.service;


import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import com.vti.rw41.FinalExam.repository.IDepartmentRepository;
import com.vti.rw41.FinalExam.specification.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    IDepartmentRepository repository;

    @Override
    public Page<Department> getAllDepartments(Pageable pageable,
                                              String search,
                                              DepartmentFilterForm filterForm) {
        Specification<Department> where = DepartmentSpecification.buildWhere(search, filterForm);
        return repository.findAll(where,pageable);
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        return Optional.of(repository.getById(id));
    }
}
