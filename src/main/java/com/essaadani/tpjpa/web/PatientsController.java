package com.essaadani.tpjpa.web;

import com.essaadani.tpjpa.entities.Patient;
import com.essaadani.tpjpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PatientsController {
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAll(){
       try{
           List<Patient> patients = patientRepository.findAll();

           if(patients.isEmpty())
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);

           return new ResponseEntity<>(patients, HttpStatus.OK);
       }catch(Exception ex){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id){
        try{
            Optional<Patient> patientOptional = patientRepository.findById(id);

            if(!patientOptional.isPresent())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> create(@RequestBody Patient patient){
         try{
            Patient _patient = patientRepository.save(new Patient(null, patient.getNom(), patient.getDateNaissance(), patient.getScore(), patient.isMalade(), patient.getMedecin()));

            return new ResponseEntity<>(patient, HttpStatus.OK);
         }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient){
        try{
            Optional<Patient> patientOptional = patientRepository.findById(id);

            if(patientOptional.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            Patient _patient = patientOptional.get();
            _patient.setNom(patient.getNom());
            _patient.setDateNaissance(patient.getDateNaissance());
            _patient.setMalade(patient.isMalade());
            _patient.setMedecin(patient.getMedecin());
            _patient.setScore(patient.getScore());
            patientRepository.save(_patient);

            return new ResponseEntity<>(_patient, HttpStatus.OK);

        }catch(Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Patient> delete(@PathVariable Long id){
        try{
            patientRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
