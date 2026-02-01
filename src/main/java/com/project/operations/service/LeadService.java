package com.project.operations.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.project.operations.model.Lead;
import com.project.operations.repository.Repository;

@Service
public class LeadService {

	private final Repository leadRepository;

    public LeadService(Repository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead saveLead(Lead lead) {

        // Business rule: default status
        if (lead.getStatus() == null || lead.getStatus().isEmpty()) {
            lead.setStatus("NEW");
        }

        return leadRepository.save(lead);
    }
    
    // READ - get all leads (ADMIN)
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // UPDATE - update lead
    public Lead updateLead(Long id, Lead updatedLead) {
        Lead existingLead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));

        existingLead.setFullName(updatedLead.getFullName());
        existingLead.setEmail(updatedLead.getEmail());
        existingLead.setPhoneNumber(updatedLead.getPhoneNumber());
        existingLead.setInterestedProgram(updatedLead.getInterestedProgram());
        existingLead.setCareerGoal(updatedLead.getCareerGoal());
        existingLead.setStatus(updatedLead.getStatus());

        return leadRepository.save(existingLead);
    }

    // DELETE - delete lead
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }
}
