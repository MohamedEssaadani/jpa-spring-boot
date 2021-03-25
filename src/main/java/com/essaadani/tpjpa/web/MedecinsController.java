package com.essaadani.tpjpa.web;

import com.essaadani.tpjpa.entities.Medecin;
import com.essaadani.tpjpa.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedecinsController {
    @Autowired
    MedecinRepository medecinRepository;

    @GetMapping("/medecins")
    public ResponseEntity<Medecin> getAll(){
        try{
            List<Medecin> medecinList = medecinRepository.findAll();

            if(medecinList.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(medecinList, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
