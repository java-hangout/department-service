package com.jh.tds.ds.service;

import com.jh.tds.ds.model.BusinessUnit;
import com.jh.tds.ds.repository.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessUnitService {

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    BUSequenceGeneratorService buSequenceGeneratorService;

    /**
     * Save a Business Unit (Create/Update)
     */
    public BusinessUnit save(BusinessUnit businessUnit) {
        businessUnit.setId(buSequenceGeneratorService.generateBusinessUnitId());
                return businessUnitRepository.save(businessUnit);
    }

    /**
     * Find a Business Unit by ID
     */
    public Optional<BusinessUnit> findById(String id) {
        return businessUnitRepository.findById(id);
    }

    /**
     * Get all Business Units
     */
    public List<BusinessUnit> findAll() {
        return businessUnitRepository.findAll();
    }

    /**
     * Delete a Business Unit by ID
     */
    public void deleteById(String id) {
        businessUnitRepository.deleteById(id);
    }
}
