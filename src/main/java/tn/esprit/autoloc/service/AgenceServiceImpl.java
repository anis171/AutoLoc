
package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence entite) {
        if (entite.getIdAgence() != null)
            throw new IllegalArgumentException("Nouvelle agence sans identifiant");
        valider(entite);
        return agenceRepository.save(entite);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence entite) {
        Agence existante = findById(id);
        valider(entite);
        existante.setNom(entite.getNom());
        existante.setVille(entite.getVille());
        existante.setAdresse(entite.getAdresse());
        existante.setTelephone(entite.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id))
            throw new ResourceNotFoundException("Agence", id);
        agenceRepository.deleteById(id);
    }

    private void valider(Agence agence) {
        if (agence.getNom() == null || agence.getNom().isBlank())
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
    }
}
