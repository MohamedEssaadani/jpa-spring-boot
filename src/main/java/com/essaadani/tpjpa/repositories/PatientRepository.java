package com.essaadani.tpjpa.repositories;

import com.essaadani.tpjpa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByNomContains(String motCle);
}
