package com.vti.rw41.FinalExam.repository;

import com.vti.rw41.FinalExam.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    Page<Department> findAll(Specification<Department> where, Pageable pageable);

}
