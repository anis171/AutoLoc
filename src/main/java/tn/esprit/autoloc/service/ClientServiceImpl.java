
package tn.esprit.autoloc.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client entite) {
        if (entite.getIdClient() != null)
            throw new IllegalArgumentException("Nouveau client sans identifiant");
        valider(entite);
        return clientRepository.save(entite);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client update(Long id, Client entite) {
        Client existant = findById(id);
        valider(entite);
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setEmail(entite.getEmail());
        existant.setTelephone(entite.getTelephone());
        existant.setNumPermis(entite.getNumPermis());
        return clientRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id))
            throw new ResourceNotFoundException("Client", id);
        clientRepository.deleteById(id);
    }

    private void valider(Client client) {
        if (client.getNom() == null || client.getNom().isBlank())
            throw new IllegalArgumentException("Le nom du client est obligatoire");

        if (client.getEmail() == null || client.getEmail().isBlank())
            throw new IllegalArgumentException("L'e-mail du client est obligatoire");
    }
}
