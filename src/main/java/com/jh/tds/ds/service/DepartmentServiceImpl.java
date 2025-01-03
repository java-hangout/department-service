package com.jh.tds.ds.service;

import com.jh.tds.ds.exception.DepartmentNotFoundException;
import com.jh.tds.ds.exception.DuplicateDepartmentNameException;
import com.jh.tds.ds.model.Department;
import com.jh.tds.ds.registry.DepartmentRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SequenceGeneratorService sequenceService;  // Inject SequenceService


    // Method to create a new department
    @Override
    public Department createDepartment(Department department) {
        try {
            // Check if department with the same name already exists
            if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
                throw new DuplicateDepartmentNameException(department.getDepartmentName());
            }
            // Generate a new department ID using the sequence service
            String departmentId = sequenceService.generateDepartmentId();
            department.setId(departmentId);
//            return departmentRepository.save(department);
            // Attempt to save the department with the given departmentName
            return departmentRepository.save(department);
        } catch (DuplicateKeyException e) {
            // Handle the duplicate departmentName error
            throw new IllegalArgumentException("Department name already exists. Please choose a different name.");
        }

    }

    // Method to get all departments
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Method to get a department by ID
    @Override
    public Department getDepartmentById(String id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        Optional<Department> department = departmentRepository.findByDepartmentName(departmentName);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(departmentName);
        }
    }

    // Method to update a department by ID
    @Override
    public Department updateDepartment(String id, Department department) {
        if (departmentRepository.existsById(id)) {
            department.setId(id);
            return departmentRepository.save(department);
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }

    // Method to delete a department by ID
    @Override
    public void deleteDepartment(String id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }
}
