package es.rodrimmb.demo.dao;

import es.rodrimmb.demo.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientDao {

    int insertClient(UUID id, Client client);

    default int insertClient(Client client) {
        UUID id = UUID.randomUUID();
        return insertClient(id, client);
    }

    Optional<Client> selectClientById(UUID id);

    List<Client> selectAllClient();

    int deleteClientById(UUID id);

    int updateClientById(UUID id, Client client);
}
