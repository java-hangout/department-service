package com.jh.tds.ds.service;

import com.jh.tds.ds.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * @author Veeresh N
 * @version 1.0
 */
public interface DepartmentService {
    public Department createDepartment(Department department);

    public List<Department> getAllDepartments();

    public Department getDepartmentById(String id);
    public Department getDepartmentByName(String name);

    public Department updateDepartment(String id, Department department);

    public void deleteDepartment(String id);
    public List<Department> getDepartmentsByBusinessUnitId(String businessUnitId);
}
