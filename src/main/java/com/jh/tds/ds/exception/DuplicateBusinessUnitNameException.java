package com.jh.tds.ds.exception;

public class DuplicateBusinessUnitNameException extends RuntimeException {

    public DuplicateBusinessUnitNameException(String businessUnitName) {
        super("BusinessUnit with name '" + businessUnitName + "' already exists.");
    }
}

