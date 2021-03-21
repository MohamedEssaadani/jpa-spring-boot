package com.essaadani.tpjpa.repositories;

import com.essaadani.tpjpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByNomContains(String motCle);
    // utilisé la pagination, il va retourner la liste des patients avec la pagination
    public Page<Patient> findByNomContains(String motCle, Pageable pageable);
    public List<Patient> findByMalade(boolean estMalade);
    public List<Patient> findByNomContainsAndMalade(String motCle, boolean estMalade);
}
