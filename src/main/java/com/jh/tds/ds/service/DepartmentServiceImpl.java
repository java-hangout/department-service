package com.jh.tds.ds.service;

import com.jh.tds.ds.exception.BusinessUnitNotFoundException;
import com.jh.tds.ds.exception.DepartmentNotFoundException;
import com.jh.tds.ds.exception.DuplicateDepartmentNameException;
import com.jh.tds.ds.model.BusinessUnit;
import com.jh.tds.ds.model.Department;
import com.jh.tds.ds.repository.BusinessUnitRepository;
import com.jh.tds.ds.repository.DepartmentRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SequenceGeneratorService sequenceService;  // Inject SequenceService

    @Autowired
    private DeptAuditLogService auditLogService;

    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;


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
            System.out.println("departmentId : " + departmentId);
            department.setId(departmentId);
            department.setCreatedDate(new Date());
            department.setUpdatedDate(new Date());
            System.out.println("department.isParentDepartment() : " + department.isParentDeptFlag());
            System.out.println("department.getParentDepartmentId() : " + department.getParentDepartmentId());
            String buID = department.getBusinessUnitId();
            Optional<BusinessUnit> businessUnitOptional = businessUnitService.findById(buID);
            System.out.println("businessUnitOptional : " + businessUnitOptional);
            BusinessUnit businessUnit = null;
                if(businessUnitOptional.isPresent()){
                    businessUnit = businessUnitOptional.get();
                    department.setBusinessUnitName(businessUnit.getBusinessUnitName());
                } else {
                    throw new BusinessUnitNotFoundException(buID);
                }
            if (department.isParentDeptFlag()) {
                businessUnit.getDepartmentIds().add(departmentId);
                businessUnitRepository.save(businessUnit);
            } else if (department.getParentDepartmentId() != null) {
                String parentId = department.getParentDepartmentId();
                Optional<Department> departmentOptional = departmentRepository.findById(parentId);
                System.out.println("departmentOptional : " + departmentOptional);
                if(departmentOptional.isPresent()){
                    System.out.println("inside is Present....!!");
                    Department department1 = departmentOptional.get();
                    System.out.println("department1.getSubDepartmentIds before : " + department1.getSubDepartmentIds());
                    department1.getSubDepartmentIds().add(departmentId);
                    System.out.println("department1.getSubDepartmentIds after : " + department1.getSubDepartmentIds());
                    departmentRepository.save(department1);
                } else {
                    throw new DepartmentNotFoundException(parentId);
                }
            }
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

    @Override
    public List<Department> getDepartmentsByBusinessUnitId(String businessUnitId) {
        Optional<List<Department>> department = departmentRepository.findByBusinessUnitId(businessUnitId);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(businessUnitId);
        }
    }

    // Method to update a department by ID
    @Override
    public Department updateDepartment(String id, Department department) {
        Department updatedDept = null;
        Optional<Department> existingDept = departmentRepository.findById(id);
//        if (departmentRepository.existsById(id)) {
        if (existingDept.isPresent()) {
            department.setId(id);
            updatedDept = departmentRepository.save(department);
            auditLogService.logChangesForAudit(existingDept.get(),"Update");
        } else {
            throw new DepartmentNotFoundException(id);
        }
        return updatedDept;
    }

    // Method to delete a department by ID
    @Override
    public void deleteDepartment(String id) {
//        if (departmentRepository.existsById(id)) {
        Optional<Department> existingDept = departmentRepository.findById(id);
        if (existingDept.isPresent()) {
            departmentRepository.deleteById(id);
            auditLogService.logChangesForAudit(existingDept.get(),"Delete");
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }
}
