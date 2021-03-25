package com.essaadani.tpjpa.web;

import com.essaadani.tpjpa.entities.Medecin;
import com.essaadani.tpjpa.entities.Patient;
import com.essaadani.tpjpa.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class MedecinsController {
    @Autowired
    MedecinRepository medecinRepository;

    @GetMapping("/medecins")
    public ResponseEntity<List<Medecin>> getAll(){
        try{
            List<Medecin> medecinList = medecinRepository.findAll();

            if(medecinList.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(medecinList, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medecins/{id}")
    public ResponseEntity<Medecin> getById(@PathVariable Long id){
        try {
            Optional<Medecin> optionalMedecin = medecinRepository.findById(id);

            if(optionalMedecin.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(optionalMedecin.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/medecins")
    public ResponseEntity<Medecin> create(@RequestBody Medecin medecin){
        try{
            Medecin _medecin = medecinRepository.save(new Medecin(null, medecin.getNom(), medecin.getDateNaissance(),medecin.getSpecialite()));

            return new ResponseEntity<>(_medecin, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Medecin> update(@PathVariable Long id, @RequestBody Medecin medecin){
        try{
            Optional<Medecin> medecinOptional = medecinRepository.findById(id);

            if(medecinOptional.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            Medecin _medecin = medecinOptional.get();
            _medecin.setNom(medecin.getNom());
            _medecin.setDateNaissance(medecin.getDateNaissance());
            _medecin.setSpecialite(medecin.getSpecialite());
            medecinRepository.save(_medecin);

            return new ResponseEntity<>(_medecin, HttpStatus.OK);

        }catch(Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/medecins/{id}")
    public ResponseEntity<Medecin> delete(@PathVariable Long id){
        try{
            medecinRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
