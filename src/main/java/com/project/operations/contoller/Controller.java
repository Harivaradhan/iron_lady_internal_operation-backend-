package com.project.operations.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.operations.model.Lead;
import com.project.operations.service.LeadService;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3008"}) // React frontend
public class Controller {
    
    private final LeadService leadService;

    public Controller(LeadService leadService) {
        this.leadService = leadService;
    }

    // CREATE Lead (used by your frontend form)
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {

        Lead savedLead = leadService.saveLead(lead);
        return new ResponseEntity<>(savedLead, HttpStatus.CREATED);
    }
    
    // READ - admin dashboard
    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        return ResponseEntity.ok(leadService.getAllLeads());
    }

    // UPDATE - admin status update
    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(
            @PathVariable Long id,
            @RequestBody Lead lead) {

        Lead updatedLead = leadService.updateLead(id, lead);
        return ResponseEntity.ok(updatedLead);
    }

    // DELETE - admin delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }
}