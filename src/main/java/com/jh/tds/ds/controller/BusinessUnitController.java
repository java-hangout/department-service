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
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3001,http://172.19.10.137:3000")
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService businessUnitService;

    @PostMapping("/create")
    public ResponseEntity<BusinessUnit> createBusinessUnit(@RequestBody BusinessUnit businessUnit) {
        BusinessUnit savedBusinessUnit = businessUnitService.createBusinessUnit(businessUnit);
        return ResponseEntity.ok(savedBusinessUnit);
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
        List<BusinessUnit> businessUnits = businessUnitService.findAll();
        return ResponseEntity.ok(businessUnits);
    }

    @GetMapping("/fetch/id/{id}")
    public ResponseEntity<BusinessUnit> getBusinessUnitById(@PathVariable String id) {
        Optional<BusinessUnit> businessUnit = businessUnitService.findById(id);
        return businessUnit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fetch/name/{businessUnit}")
    public ResponseEntity<BusinessUnit> getBusinessUnitByName(@PathVariable String businessUnitName) {
        Optional<BusinessUnit> businessUnit = businessUnitService.findByBusinessUnit(businessUnitName);
        return businessUnit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BusinessUnit> updateBusinessUnit(@PathVariable String id, @RequestBody BusinessUnit businessUnit) {
        BusinessUnit UpdatedBusinessUnit = businessUnitService.updateBusinessUnit(id, businessUnit);
        return ResponseEntity.ok(UpdatedBusinessUnit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBusinessUnit(@PathVariable String id) {
            businessUnitService.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}
