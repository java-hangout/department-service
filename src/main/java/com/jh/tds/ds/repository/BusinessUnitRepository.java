package com.jh.tds.ds.repository;

import com.jh.tds.ds.model.BusinessUnit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessUnitRepository extends MongoRepository<BusinessUnit, String> {
    boolean existsByBusinessUnitName(String businessUnitName);

    Optional<BusinessUnit> findByBusinessUnitName(String businessUnitName);
}
