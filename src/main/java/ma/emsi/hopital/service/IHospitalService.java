package ma.emsi.hopital.service;

import ma.emsi.hopital.entities.Consultation;
import ma.emsi.hopital.entities.Medcin;
import ma.emsi.hopital.entities.Patient;
import ma.emsi.hopital.entities.RendezVous;

public interface IHospitalService {
     Patient savePatient(Patient patient);
    Medcin saveMedecin(Medcin medcin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
