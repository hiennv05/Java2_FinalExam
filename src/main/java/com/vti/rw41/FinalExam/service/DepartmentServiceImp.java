package com.vti.rw41.FinalExam.service;


import com.vti.rw41.FinalExam.dto.request.DepartmentRequest;
import com.vti.rw41.FinalExam.entity.Account;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import com.vti.rw41.FinalExam.repository.IAccountRepository;
import com.vti.rw41.FinalExam.repository.IDepartmentRepository;
import com.vti.rw41.FinalExam.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    IDepartmentRepository repository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<Department> getAllDepartments(Pageable pageable,
                                              String search,
                                              DepartmentFilterForm filterForm) {
        Specification<Department> where = DepartmentSpecification.buildWhere(search, filterForm);
        return repository.findAll(where, pageable);
    }

    @Override
    public Optional<Department> getDepartmentById(Integer id) {
        return Optional.of(repository.getById(id));
    }

    @Override
    @Transactional
    public Department createDepartment(DepartmentRequest request) {
        Department departmentEntity = modelMapper.map(request, Department.class);

        Department department = repository.save(departmentEntity);

//        List<Account> accountEntities = departmentEntity.getAccounts();
//        accountEntities.forEach(account -> account.setDepartment(department));
//        accountRepository.saveAll(accountEntities);

        return department;
    }

}
