
package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IClientRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepositoryTests {

    @Autowired
    private IAgenceRepository agenceRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Test
    void testerCRUDAgence() {

        // 1. Créer une agence
        Agence agence = new Agence();
        agence.setNom("Agence Test");
        agence.setVille("Tunis");
        agence.setAdresse("Centre-ville");
        agence.setTelephone("70111222");

        // 2. Enregistrer l'agence
        Agence agenceSauvegardee = agenceRepository.save(agence);
        assertNotNull(agenceSauvegardee.getIdAgence());

        Long id = agenceSauvegardee.getIdAgence();

        // 3. Afficher toutes les agences
        System.out.println("Toutes les agences : "
                + agenceRepository.findAll());

        // 4. Rechercher par identifiant
        assertTrue(agenceRepository.findById(id).isPresent());

        // 5. Vérifier l'existence
        assertTrue(agenceRepository.existsById(id));

        // 6. Compter les agences
        assertTrue(agenceRepository.count() >= 1);

        // 7. Supprimer l'agence
        agenceRepository.deleteById(id);

        // 8. Vérifier la suppression
        assertFalse(agenceRepository.existsById(id));
    }

    @Test
    void testerCRUDClient() {

        // 1. Créer le premier client
        Client client1 = new Client();
        client1.setNom("Ben Ali");
        client1.setPrenom("Ahmed");
        client1.setEmail("ahmed.test@example.com");
        client1.setTelephone("22111222");
        client1.setNumPermis("TEST-PERMIS-001");
        client1.setDateInscription(LocalDate.now());

        // 2. Créer le deuxième client
        Client client2 = new Client();
        client2.setNom("Trabelsi");
        client2.setPrenom("Sami");
        client2.setEmail("sami.test@example.com");
        client2.setTelephone("22111333");
        client2.setNumPermis("TEST-PERMIS-002");
        client2.setDateInscription(LocalDate.now());

        // 3. Enregistrer les clients
        client1 = clientRepository.save(client1);
        client2 = clientRepository.save(client2);

        // 4. Vérifier les identifiants générés
        assertNotNull(client1.getIdClient());
        assertNotNull(client2.getIdClient());

        // 5. Afficher tous les clients
        System.out.println("Tous les clients : "
                + clientRepository.findAll());

        // 6. Rechercher le premier client par identifiant
        assertTrue(
                clientRepository.findById(client1.getIdClient()).isPresent()
        );

        // 7. Vérifier l'existence du premier client
        assertTrue(
                clientRepository.existsById(client1.getIdClient())
        );

        // 8. Vérifier qu'un identifiant inexistant n'existe pas
        assertFalse(clientRepository.existsById(-1L));

        // 9. Vérifier le nombre de clients enregistrés
        assertTrue(clientRepository.count() >= 2);

        // 10. Supprimer le premier client
        Long idClient = client1.getIdClient();
        clientRepository.deleteById(idClient);

        // 11. Vérifier la suppression
        assertFalse(clientRepository.existsById(idClient));
    }
}
