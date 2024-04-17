package ma.emsi.hopital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private Date date;
    //dans bdd enum va enregistrer sous forme string
    @Enumerated(EnumType.STRING)
    private StatusRDV status;
    @ManyToOne
    //il considere la relation dans le cas ajouter mais pas dans lister
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private Patient patient;
    @ManyToOne
    private Medcin medcin;
    //ajouter un attribut relation bidiectionel
    @OneToOne(mappedBy = "rendezVous")
    //on met ici mappedby pour que la cle etrangaire ce met dans consultation
    private Consultation consultation;
}
