package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "client")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idClient;

    @Column(nullable = false, length = 50)
    String nom;

    @Column(nullable = false, length = 50)
    String prenom;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    @Column(nullable = false, length = 20)
    String telephone;

    @Column(nullable = false, unique = true, length = 30)
    String numPermis;

    @Column(nullable = false)
    LocalDate dateInscription;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return Objects.equals(nom, client.nom)
                && Objects.equals(prenom, client.prenom)
                && Objects.equals(email, client.email)
                && Objects.equals(telephone, client.telephone)
                && Objects.equals(numPermis, client.numPermis)
                && Objects.equals(dateInscription, client.dateInscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                nom,
                prenom,
                email,
                telephone,
                numPermis,
                dateInscription
        );
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", numPermis='" + numPermis + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}