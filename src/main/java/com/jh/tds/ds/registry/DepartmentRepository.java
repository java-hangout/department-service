package com.jh.tds.ds.registry;


import com.jh.tds.ds.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Optional<Department> findByDepartmentName(String departmentName);

    // Custom query to check if a department with a name already exists
    boolean existsByDepartmentName(String departmentName);

}
