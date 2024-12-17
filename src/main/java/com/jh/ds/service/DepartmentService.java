package com.jh.ds.service;


import com.jh.ds.model.Department;
import com.jh.ds.registry.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
