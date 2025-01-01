package com.jh.tds.ds.exception;

public class DuplicateDepartmentNameException extends RuntimeException {

    public DuplicateDepartmentNameException(String departmentName) {
        super("Department with name '" + departmentName + "' already exists.");
    }
}

