package com.jh.tds.ds.repository;

import com.jh.tds.ds.model.BusinessUnit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitRepository extends MongoRepository<BusinessUnit, String> {
    // Custom queries can be added here if necessary
}
