
package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation entite) {
        if (entite.getIdReservation() != null)
            throw new IllegalArgumentException("Nouvelle réservation sans identifiant");
        valider(entite);
        return reservationRepository.save(entite);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation entite) {
        Reservation existante = findById(id);
        valider(entite);
        existante.setDateDebut(entite.getDateDebut());
        existante.setDateFin(entite.getDateFin());
        return reservationRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id))
            throw new ResourceNotFoundException("Réservation", id);
        reservationRepository.deleteById(id);
    }

    private void valider(Reservation reservation) {
        if (reservation.getDateDebut() != null
                && reservation.getDateFin() != null
                && reservation.getDateFin().isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin doit être postérieure ou égale à la date de début"
            );
        }
    }
}
