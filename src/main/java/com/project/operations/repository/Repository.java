package com.project.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.operations.model.Lead;

public interface Repository extends JpaRepository<Lead, Long> {

    // Optional: used later to avoid duplicates
    boolean existsByEmail(String email);

}
