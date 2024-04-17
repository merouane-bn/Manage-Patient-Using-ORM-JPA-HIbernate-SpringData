package ma.emsi.hopital.repositories;

import ma.emsi.hopital.entities.Medcin;
import ma.emsi.hopital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedcinRepository extends JpaRepository<Medcin,Long> {
    Medcin findByNom(String nom);
}
