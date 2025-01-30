package com.jh.tds.ds.repository;

import com.jh.tds.ds.model.DeptAuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeptAuditLogRepository extends MongoRepository<DeptAuditLog, String> {
}
