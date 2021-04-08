package com.essaadani.tpjpa;

import com.essaadani.tpjpa.entities.Medecin;
import com.essaadani.tpjpa.entities.Patient;
import com.essaadani.tpjpa.repositories.MedecinRepository;
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
    @Autowired
    MedecinRepository medecinRepository;

    public static void main(String[] args) {
        SpringApplication.run(TpJpaApplication.class, args);
    }



    @Override
    public void run(String[] args) throws Exception {


        //Ajouter des patients
        patientRepository.save(new Patient(null, "Mohamed", new Date(), 9887, false, medecinRepository.findById(1L).get()));
        patientRepository.save(new Patient(null, "Asmaa", new Date(), 3434, false, medecinRepository.findById(1L).get()));
        patientRepository.save(new Patient(null, "Adam", new Date(), 6666, false, medecinRepository.findById(1L).get()));
        patientRepository.save(new Patient(null, "Hicham", new Date(), 22222, false, medecinRepository.findById(1L).get()));
        patientRepository.save(new Patient(null, "Saloi", new Date(), 8758, false, medecinRepository.findById(1L).get()));
        patientRepository.save(new Patient(null, "Khalid", new Date(), 8758, true, medecinRepository.findById(1L).get()));

        System.out.println("************************************");
        //Afficher tous les patients
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });


        System.out.println("************************************");
        //rechercher un patient par son id
        Patient patient = patientRepository.findById(4L).get();
        System.out.println(patient.toString());


        System.out.println("************************************");
        //rechercher les patients  par nom
        patientRepository.findByNomContains("m")
                .forEach(p->{
                    System.out.println(p.getNom());
                });


        System.out.println("************************************");
        //rechercher les patients par malade
        patientRepository.findByMalade(true)
                .forEach(p->{
                    System.out.println(p.getNom());
                });

        System.out.println("************************************");
        //rechercher les patients par nom et malade
        patientRepository.findByNomContainsAndMalade("K", true)
                .forEach(p->{
                    System.out.println(p.getNom());
                });

        //Supprimer un patient
        patientRepository.deleteById(1L);

        System.out.println("************************************");
        patientRepository.findAll()
              .forEach(p->{
               System.out.println(p.getNom());
         });

        System.out.println("************ utilisé la pagination ******************");

        //Pagination
        //la pagination (donne moi dans la page 1, deux patients)
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


        /*
        //Medecins
        //Ajouter des medecins
        medecinRepository.save(new Medecin(null, "Es-saadani", new Date(), "General"));
        medecinRepository.save(new Medecin(null, "Oukil", new Date(), "Dentiste"));
        medecinRepository.save(new Medecin(null, "Naji", new Date(), "General"));
        medecinRepository.save(new Medecin(null, "Alami", new Date(), "Psychiatre"));

        //Afficher tous les medecins
        medecinRepository.findAll()
                .forEach(m->
                {
                    System.out.println(m.getNom());
                });


        //Afficher un medecin par son id
       //Medecin medecin = medecinRepository.findById(3L).get();
        //System.out.println(medecin.getNom());

        //Mettre a jour un medecin
        Medecin medecin = medecinRepository.findById(3L).get();
        medecin.setNom("Ouahbi");
        medecinRepository.save(medecin);

        //Supprimer un medecin
        medecinRepository.deleteById(4L);


        System.out.println("Testez la liste des medecins apres la suppression : ");
        medecinRepository.findAll()
              .forEach(m->
              {
                  System.out.println(m.toString());
              });
        //chercher des medecins avec differents critere
        System.out.println("Recherche par criteres  : ");

        medecinRepository.findByNomContainsAndSpecialiteContains("a", "g")
                    .forEach(m->{
                    System.out.println(m.getNom());
               });

        //Tester la pagination
          Page<Medecin> pageMedecins = medecinRepository.findAll(PageRequest.of(1, 2));
           List<Medecin> listMedecins = pageMedecins.getContent();
           listMedecins.forEach(m->{
           System.out.println(m.getNom());
            });

    }*/
    }



}
