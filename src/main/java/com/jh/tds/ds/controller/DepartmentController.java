package com.jh.tds.ds.controller;

import com.jh.tds.ds.model.Department;
import com.jh.tds.ds.service.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Department Management", description = "Endpoints for managing departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    // Create a new department
    @PostMapping("/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    // Get all departments
    @GetMapping("/fetch/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Get a department by ID
    @GetMapping("/fetch/id/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }
    // Get a department by ID
    @GetMapping("/fetch/name/{departmentName}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String departmentName) {
        Department department = departmentService.getDepartmentByName(departmentName);
        return ResponseEntity.ok(department);
    }

    // Update a department by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String id, @RequestBody Department department) {
            System.out.println("request received id "+id +" department "+department);
            Department updatedDepartment = departmentService.updateDepartment(id, department);
            return ResponseEntity.ok(updatedDepartment);

    }

    // Delete a department by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
