package com.essaadani.tpjpa.repositories;

import com.essaadani.tpjpa.entities.Medecin;
import com.essaadani.tpjpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    public List<Medecin> findByNomContainsAndSpecialiteContains(String nom, String specialite);

}
