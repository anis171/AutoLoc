package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "maintenance")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idMaintenance;

    @Column(nullable = false)
    LocalDate dateDebut;

    @Column(nullable = false)
    LocalDate dateFin;

    @Column(nullable = false, length = 500)
    String description;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance maintenance = (Maintenance) o;

        return Objects.equals(dateDebut, maintenance.dateDebut)
                && Objects.equals(dateFin, maintenance.dateFin)
                && Objects.equals(description, maintenance.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateDebut, dateFin, description);
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", description='" + description + '\'' +
                '}';
    }
}