package com.jh.tds.ds.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "dept_audit_logs")
public class DeptAuditLog {

    @Id
    private String id;
    private String action;           // Action type: insert, update, delete
    private String deptId;
    private String documentDetails;  // The details of the changed document (JSON string)
    private Date timestamp;          // The timestamp of when the change occurred

    public DeptAuditLog(String action, String deptId, String documentDetails) {
        this.action = action;
        this.deptId = deptId;
        this.documentDetails = documentDetails;
        this.timestamp = new Date();
    }
}
