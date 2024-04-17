package ma.emsi.hopital;

import ma.emsi.hopital.entities.*;
import ma.emsi.hopital.repositories.MedcinRepository;
import ma.emsi.hopital.repositories.PatientRepository;
import ma.emsi.hopital.repositories.RendezVousRepository;
import ma.emsi.hopital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
/*    La façon la plus simple est d'implémenter l'interface CommandLineRunner et de redéfinir la méthode run.
    La deuxième solution est de créer une méthode CommandLineRunner */
public class HopitalApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }
    @Bean /*methode qui va s'exécuter au démarrage
    cette methode start va return un obj il devient un composant spring il va te donner un obj et il va le placer dans son contexte
    si on veux lutiliser @autowired
    si on  ajoute un obj auto il fait injection des dependances  start() */
    /*  CommandLineRunner start(PatientRepository patientRepository,
                            MedcinRepository medecinRepository,
                            RendezVousRepository rendezVousRepository,
                           ConsultationRepository consultationRepository)*/
    //injecter la couche metier
    CommandLineRunner start(IHospitalService hospitalService, MedcinRepository medecinRepository,
                            PatientRepository patientRepository,RendezVousRepository rendezVousRepository ){
    return args ->{
        Stream.of("Mohamed","Hassan","Najat")
                .forEach(name->{
                     Patient patient=new Patient();
                     patient.setNom(name);
                     patient.setDateNaissance(new Date());
                     patient.setMalade(false);
                     //patientRepository.save(patient);
                     hospitalService.savePatient(patient);

                });
        Stream.of("merouan","ali","hanaa")
                .forEach(name->{
                    Medcin medecin = new Medcin();
                    medecin.setNom(name);
                    medecin.setEmail(name+"@gmail.com");
                    medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
//                    medecinRepository.save(medecin);
                    hospitalService.saveMedecin(medecin);
                });
        Patient patient=patientRepository.findById(1L).orElse(null);
        Patient patient1=patientRepository.findByNom("Mohamed");

        Medcin medecin=medecinRepository.findByNom("hanaa");

        RendezVous rendezVous=new RendezVous();
        rendezVous.setDate(new Date());
        rendezVous.setStatus(StatusRDV.PENDING);
        rendezVous.setMedcin(medecin);
        rendezVous.setPatient(patient);
//        rendezVousRepository.save(rendezVous);
         RendezVous saveDRDV = hospitalService.saveRDV(rendezVous);
        System.out.println(saveDRDV.getId());
        //la methode save retourne obj si on a besion on l'utilise si on a pas besoin on le garde


//        RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
        RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
        Consultation consultation=new Consultation();
        consultation.setDateConsultation(new Date());
        consultation.setRendezVous(rendezVous1);
        consultation.setRapport("Rapport de la consultation ....");
//        consultationRepository.save(consultation);
        hospitalService.saveConsultation(consultation);
        };

    }
    }

