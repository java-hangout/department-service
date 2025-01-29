package com.jh.tds.ds.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "departments")
public class Department {

    @Id
    private String id;                     // Unique department ID
    @Indexed(unique = true)
    private String departmentName;                   // Name of the department
    private String description;            // Description of the department
//    private String managerId;              // Reference to the manager (One-to-One, Manager of the department)
    private List<String> userIds;          // List of user IDs who are part of the department (Many-to-One)

}

