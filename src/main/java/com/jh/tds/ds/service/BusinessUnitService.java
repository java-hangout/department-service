package com.jh.tds.ds.service;

import com.jh.tds.ds.exception.BusinessUnitNotFoundException;
import com.jh.tds.ds.exception.DuplicateBusinessUnitNameException;
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
    public BusinessUnit createBusinessUnit(BusinessUnit businessUnit) {
        if (businessUnitRepository.existsByBusinessUnitName(businessUnit.getBusinessUnitName())) {
            throw new DuplicateBusinessUnitNameException(businessUnit.getBusinessUnitName());
        }
        businessUnit.setId(buSequenceGeneratorService.generateBusinessUnitId());
        return businessUnitRepository.save(businessUnit);
    }

    public BusinessUnit updateBusinessUnit(String id, BusinessUnit businessUnit) {
        Optional<BusinessUnit> existingBusinessUnit = businessUnitRepository.findById(id);
        if (existingBusinessUnit.isPresent()) {
            businessUnit.setId(id); // Update the ID
            return businessUnitRepository.save(businessUnit);
        } else {
            throw new BusinessUnitNotFoundException(id);
        }
    }
    /**
     * Find a Business Unit by ID
     */
    public Optional<BusinessUnit> findById(String id) {
        return businessUnitRepository.findById(id);
    }

    public Optional<BusinessUnit> findByBusinessUnit(String id) {
        return businessUnitRepository.findByBusinessUnitName(id);
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
        Optional<BusinessUnit> existingBusinessUnit = businessUnitRepository.findById(id);
        if (existingBusinessUnit.isPresent()) {
            businessUnitRepository.deleteById(id);
        } else {
            throw new BusinessUnitNotFoundException(id);
        }

    }
}
