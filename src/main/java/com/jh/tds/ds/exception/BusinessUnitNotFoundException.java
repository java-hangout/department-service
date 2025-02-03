package com.jh.tds.ds.exception;

public class BusinessUnitNotFoundException extends RuntimeException {

    public BusinessUnitNotFoundException(String id) {
        super("Business Unit " + id + " not found");
    }
}