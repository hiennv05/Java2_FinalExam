package com.vti.rw41.FinalExam.service;

import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.enumurations.RoleDepartment;
import com.vti.rw41.FinalExam.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Service
public class DepartmentServiceImp implements IDepartmentService {
    @Autowired
    IDepartmentRepository repository;

//    @PostConstruct
//    public void init() {
//        for (int i = 0; i < 20; i++) {
//            Department department = new Department();
//            department.setName("department_" + (i + 1));
//            if (i <5) {
//                department.setTotal_member(1);
//                department.setType(RoleDepartment.PM);
//            } else if (i <10) {
//                department.setTotal_member(2);
//                department.setType(RoleDepartment.SALE);
//            } else if (i <15) {
//                department.setTotal_member(3);
//                department.setType(RoleDepartment.BOD);
//            } else {
//                department.setTotal_member(4);
//                department.setType(RoleDepartment.DEV);
//            }
//            department.setCreate_date(LocalDate.now());
//            repository.save(department);
//        }
//
//    }
}
