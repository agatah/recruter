package pl.unity.vgp.recruter.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.unity.vgp.recruter.domain.model.Client;
import pl.unity.vgp.recruter.domain.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void saveClientsData(Iterable<Client> clients) {

        for(Client client: clients){
            Optional<Client> clientOpt = clientRepository.findByName(client.getName());

            if(clientOpt.isPresent()){
                Client dbClient = clientOpt.get();
                dbClient.setAddress(client.getAddress())
                        .setContact(client.getContact())
                        .setLocation(client.getLocation());

                clientRepository.save(dbClient);

            } else {
                clientRepository.save(client);
            }
        }
    }
}
