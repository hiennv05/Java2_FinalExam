package com.vti.rw41.FinalExam.controller;

import com.vti.rw41.FinalExam.dto.response.DepartmentDto;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.service.DepartmentServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    @Autowired
    DepartmentServiceImp service;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {
        Page<Department> entity = service.getAllDepartments(pageable);

        List<DepartmentDto> dto = modelMapper.map(entity.getContent(),
                new TypeToken<List<DepartmentDto>>() {
                }.getType());
        Page<DepartmentDto> dtoPage = new PageImpl<>(dto, pageable, entity.getTotalElements());
        return dtoPage;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<DepartmentDto> getDepartmentById(@PathVariable Integer id) {
        Optional<Department> entity = service.getDepartmentById(id);
        DepartmentDto departmentDto = null;
        if (entity.isPresent()) {
            departmentDto = modelMapper.map(entity.get(), DepartmentDto.class);
        }
        return Optional.ofNullable(departmentDto);
    }
}
