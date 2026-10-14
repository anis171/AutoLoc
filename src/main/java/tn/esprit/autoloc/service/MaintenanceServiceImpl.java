
package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance entite) {
        if (entite.getIdMaintenance() != null)
            throw new IllegalArgumentException("Nouvelle maintenance sans identifiant");
        valider(entite);
        return maintenanceRepository.save(entite);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance entite) {
        Maintenance existante = findById(id);
        valider(entite);
        existante.setDateDebut(entite.getDateDebut());
        existante.setDateFin(entite.getDateFin());
        existante.setDescription(entite.getDescription());
        return maintenanceRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id))
            throw new ResourceNotFoundException("Maintenance", id);
        maintenanceRepository.deleteById(id);
    }

    private void valider(Maintenance maintenance) {
        if (maintenance.getDateDebut() != null
                && maintenance.getDateFin() != null
                && maintenance.getDateFin().isBefore(maintenance.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin doit être postérieure ou égale à la date de début"
            );
        }
    }
}
