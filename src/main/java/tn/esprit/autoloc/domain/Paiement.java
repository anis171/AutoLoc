package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "paiement")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idPaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal montant;

    @Column(nullable = false)
    LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    modePaiement modePaiement;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Paiement paiement = (Paiement) o;

        return Objects.equals(montant, paiement.montant)
                && Objects.equals(datePaiement, paiement.datePaiement)
                && modePaiement == paiement.modePaiement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant, datePaiement, modePaiement);
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "montant=" + montant +
                ", datePaiement=" + datePaiement +
                ", modePaiement=" + modePaiement +
                '}';
    }
}