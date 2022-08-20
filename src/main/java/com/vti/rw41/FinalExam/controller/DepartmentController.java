package com.vti.rw41.FinalExam.controller;

import com.vti.rw41.FinalExam.dto.request.DepartmentRequest;
import com.vti.rw41.FinalExam.dto.response.DepartmentDto;
import com.vti.rw41.FinalExam.entity.Department;
import com.vti.rw41.FinalExam.form.DepartmentFilterForm;
import com.vti.rw41.FinalExam.service.DepartmentServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin("http://127.0.0.1:5500")
public class DepartmentController {
    @Autowired
    DepartmentServiceImp service;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public Page<DepartmentDto> getAllDepartments(Pageable pageable, String search, DepartmentFilterForm filterForm) {
        Page<Department> entity = service.getAllDepartments(pageable, search, filterForm);

        List<DepartmentDto> dto = modelMapper.map(entity.getContent(), new TypeToken<List<DepartmentDto>>() {
        }
                .getType());
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

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody @Valid DepartmentRequest request) {
        Department department = service.createDepartment(request);
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

        return departmentDto;
    }
}
