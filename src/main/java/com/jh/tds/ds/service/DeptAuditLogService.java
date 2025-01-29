package com.jh.tds.ds.service;


import com.jh.tds.ds.model.DeptAuditLog;
import com.jh.tds.ds.model.Department;
import com.jh.tds.ds.registry.DeptAuditLogRepository;
import com.jh.tds.ds.util.DeptAuditLogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Veeresh N
 * @version 1.0
 */
@Service
public class DeptAuditLogService {
    @Autowired
    private DeptAuditLogRepository deptAuditLogRepository;

    public void logChangesForAudit(Department department, String action) {
        String documentDetails = DeptAuditLogUtil.convertUserToJson(department);
        DeptAuditLog deptAuditLog = new DeptAuditLog(
                action,
                department.getId(),
                documentDetails
        );
        deptAuditLogRepository.save(deptAuditLog);
    }
}
