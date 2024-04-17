package ma.emsi.hopital.service;

import jakarta.transaction.Transactional;
import ma.emsi.hopital.entities.Consultation;
import ma.emsi.hopital.entities.Medcin;
import ma.emsi.hopital.entities.Patient;
import ma.emsi.hopital.entities.RendezVous;
import ma.emsi.hopital.repositories.ConsultationRepository;
import ma.emsi.hopital.repositories.MedcinRepository;
import ma.emsi.hopital.repositories.PatientRepository;
import ma.emsi.hopital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {
    //injection par autowired ou le constructor
    private PatientRepository patientRepository;
    private MedcinRepository medcinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public IHospitalServiceImpl(PatientRepository patientRepository, MedcinRepository medcinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medcinRepository = medcinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }


    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medcin saveMedecin(Medcin medcin) {

        return medcinRepository.save(medcin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
       //generer chaine caractere aliatoire depend de la date systeme
        //utiliser dans big data
        rendezVous.setId(UUID.randomUUID().toString());

        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepository.save(consultation);
    }
}
