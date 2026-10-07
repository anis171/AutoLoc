package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Table(name = "agence")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idAgence;

    @Column(nullable = false, length = 100)
    String nom;

    @Column(nullable = false, length = 50)
    String ville;

    @Column(nullable = false, length = 150)
    String adresse;

    @Column(nullable = false, length = 20)
    String telephone;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Agence agence = (Agence) o;

        return Objects.equals(nom, agence.nom)
                && Objects.equals(ville, agence.ville)
                && Objects.equals(adresse, agence.adresse)
                && Objects.equals(telephone, agence.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, ville, adresse, telephone);
    }

    @Override
    public String toString() {
        return "Agence{" +
                "nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}