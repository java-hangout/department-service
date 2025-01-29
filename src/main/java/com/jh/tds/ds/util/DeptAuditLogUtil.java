package com.jh.tds.ds.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.tds.ds.model.Department;

public class DeptAuditLogUtil {

    public static String convertUserToJson(Department department) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(department);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
