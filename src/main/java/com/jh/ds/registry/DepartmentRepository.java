package com.jh.ds.registry;


import com.jh.ds.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByName(String name);
}
