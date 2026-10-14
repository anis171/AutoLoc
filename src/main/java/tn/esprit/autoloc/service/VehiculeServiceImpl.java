
package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule entite) {
        if (entite.getIdVehicule() != null)
            throw new IllegalArgumentException("Nouveau véhicule sans identifiant");
        valider(entite);
        return vehiculeRepository.save(entite);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Véhicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule entite) {
        Vehicule existant = findById(id);
        valider(entite);
        existant.setImmatriculation(entite.getImmatriculation());
        existant.setMarque(entite.getMarque());
        existant.setModele(entite.getModele());
        existant.setTarifJournalier(entite.getTarifJournalier());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id))
            throw new ResourceNotFoundException("Véhicule", id);
        vehiculeRepository.deleteById(id);
    }

    private void valider(Vehicule vehicule) {
        if (vehicule.getTarifJournalier() != null
                && vehicule.getTarifJournalier().signum() < 0)
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
    }
}
