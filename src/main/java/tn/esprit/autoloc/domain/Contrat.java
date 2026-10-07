package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "contrat")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idContrat;

    @Column(nullable = false)
    LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal montantTotal;

    @Column(nullable = false)
    Boolean valide;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Contrat contrat = (Contrat) o;

        return Objects.equals(dateSignature, contrat.dateSignature)
                && Objects.equals(montantTotal, contrat.montantTotal)
                && Objects.equals(valide, contrat.valide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateSignature, montantTotal, valide);
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "dateSignature=" + dateSignature +
                ", montantTotal=" + montantTotal +
                ", valide=" + valide +
                '}';
    }
}