package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idReservation;

    @Column(nullable = false)
    LocalDate dateDebut;

    @Column(nullable = false)
    LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    StatutReservation statut;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Reservation reservation = (Reservation) o;

        return Objects.equals(dateDebut, reservation.dateDebut)
                && Objects.equals(dateFin, reservation.dateFin)
                && statut == reservation.statut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateDebut, dateFin, statut);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut=" + statut +
                '}';
    }
    @ManyToOne(fetch = FetchType.LAZY)
    Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    Vehicule vehicule;

    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    Contrat contrat;
}