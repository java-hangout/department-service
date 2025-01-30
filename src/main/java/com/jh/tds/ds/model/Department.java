package com.jh.tds.ds.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Document(collection = "departments")
public class Department {
    private String businessUnitId;
    @Id
    private String id;                     // Unique department ID
    @Indexed(unique = true)
    private String departmentName;                   // Name of the department
    private String description;            // Description of the department
    private List<String> userIds;          // List of user IDs who are part of the department (Many-to-One)    private String businessUnitId;
    private String parentDepartmentId;
    private List<String> subDepartmentIds;//ist of sub-departments under this department
    @CreatedDate
    private Date createdDate;  // Stores the date when the record was created
    @LastModifiedDate
    private Date updatedDate;
    private boolean isParentDepartment;    // Flag indicating if this department is a parent department (true/false)
}

