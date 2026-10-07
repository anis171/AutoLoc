package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "vehicule")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    String immatriculation;

    @Column(nullable = false, length = 50)
    String marque;

    @Column(nullable = false, length = 50)
    String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    StatutVehicule statut;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Vehicule vehicule = (Vehicule) o;

        return Objects.equals(immatriculation, vehicule.immatriculation)
                && Objects.equals(marque, vehicule.marque)
                && Objects.equals(modele, vehicule.modele)
                && categorie == vehicule.categorie
                && Objects.equals(tarifJournalier, vehicule.tarifJournalier)
                && statut == vehicule.statut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                immatriculation,
                marque,
                modele,
                categorie,
                tarifJournalier,
                statut
        );
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", categorie=" + categorie +
                ", tarifJournalier=" + tarifJournalier +
                ", statut=" + statut +
                '}';
    }
}
