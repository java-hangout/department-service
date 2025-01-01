package com.jh.tds.ds.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String id) {
        super("Department " + id + " not found");
    }
}