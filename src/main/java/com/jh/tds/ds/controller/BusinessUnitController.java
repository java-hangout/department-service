package com.jh.tds.ds.controller;

import com.jh.tds.ds.model.BusinessUnit;
import com.jh.tds.ds.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businessunits")
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService businessUnitService;

    /**
     * Create a new Business Unit
     */
    @PostMapping("/create")
    public ResponseEntity<BusinessUnit> createBusinessUnit(@RequestBody BusinessUnit businessUnit) {
        BusinessUnit savedBusinessUnit = businessUnitService.save(businessUnit);
        return ResponseEntity.ok(savedBusinessUnit);
    }

    /**
     * Get all Business Units
     */
    @GetMapping("/fetch/all")
    public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
        List<BusinessUnit> businessUnits = businessUnitService.findAll();
        return ResponseEntity.ok(businessUnits);
    }

    /**
     * Get a Business Unit by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BusinessUnit> getBusinessUnitById(@PathVariable String id) {
        Optional<BusinessUnit> businessUnit = businessUnitService.findById(id);
        if (businessUnit.isPresent()) {
            return ResponseEntity.ok(businessUnit.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update a Business Unit
     */
    @PutMapping("/{id}")
    public ResponseEntity<BusinessUnit> updateBusinessUnit(@PathVariable String id, @RequestBody BusinessUnit businessUnit) {
        Optional<BusinessUnit> existingBusinessUnit = businessUnitService.findById(id);
        if (existingBusinessUnit.isPresent()) {
            businessUnit.setId(id); // Update the ID
            BusinessUnit updatedBusinessUnit = businessUnitService.save(businessUnit);
            return ResponseEntity.ok(updatedBusinessUnit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a Business Unit
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessUnit(@PathVariable String id) {
        Optional<BusinessUnit> existingBusinessUnit = businessUnitService.findById(id);
        if (existingBusinessUnit.isPresent()) {
            businessUnitService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
