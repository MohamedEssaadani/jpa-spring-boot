package com.essaadani.tpjpa;

import com.essaadani.tpjpa.entities.Patient;
import com.essaadani.tpjpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TpJpaApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(TpJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Mohamed", new Date(), 9887, false));
        patientRepository.save(new Patient(null, "Asmaa", new Date(), 3434, false));
        patientRepository.save(new Patient(null, "Adam", new Date(), 6666, false));
        patientRepository.save(new Patient(null, "Hicham", new Date(), 22222, false));
        patientRepository.save(new Patient(null, "Saloi", new Date(), 8758, false));
        patientRepository.save(new Patient(null, "Khalid", new Date(), 8758, true));

        System.out.println("************************************");

        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });

        System.out.println("************************************");
        Patient patient = patientRepository.findById(4L).get();
        System.out.println(patient.toString());

        System.out.println("************************************");
        patientRepository.findByNomContains("m")
                .forEach(p->{
                    System.out.println(p.getNom());
                });


        System.out.println("************************************");
        patientRepository.findByMalade(true)
                .forEach(p->{
                    System.out.println(p.getNom());
                });

        System.out.println("************************************");
        patientRepository.findByNomContainsAndMalade("K", true)
                .forEach(p->{
        System.out.println(p.getNom());
        });

        //delete:
        //patientRepository.deleteById(1L);

        //System.out.println("************************************");
        //patientRepository.findAll()
          //      .forEach(p->{
            //        System.out.println(p.getNom());
              //  });

        System.out.println("************ utilisé la pagination ******************");
        //donne moi dans la page 0, 2 patients PageRequest.of(0, 2) => Mohamed et Asmaa
        //donne moi dans la page 1, 2 patients PageRequest.of(1, 2) => Adam et Hicham
        //on a 5 enregistrement => alors on a 3 pages
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(1, 2));
        List<Patient> listPatients = pagePatients.getContent();
         listPatients.forEach(p->{
            System.out.println(p.getNom());
        });

        System.out.println(pagePatients.getTotalPages());


        System.out.println("************ Rechercher par mot clé avec la pagination ******************");
        Page<Patient> pagePatients2 = patientRepository.findByNomContains("a", PageRequest.of(2, 2));
        pagePatients2.forEach(p->{
            System.out.println(p.getNom());
        });
    }
}
