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

@Getter
@Setter
@Document(collection = "business_units")
public class BusinessUnit {

    @Id
    private String id;                        // Unique business unit ID
    @Indexed(unique = true)
    private String businessUnitName;          // Name of the business unit
    private String description;               // Description of the business unit
    private List<String> departmentIds;     // List of departments within the business unit
    @CreatedDate
    private Date createdDate;  // Stores the date when the record was created
    @LastModifiedDate
    private Date updatedDate;
}
