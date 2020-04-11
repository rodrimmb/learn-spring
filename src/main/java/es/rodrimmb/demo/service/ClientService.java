package es.rodrimmb.demo.service;

import es.rodrimmb.demo.dao.ClientDao;
import es.rodrimmb.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(@Qualifier("postgres") ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public int addClient(Client client) {
        return clientDao.insertClient(client);
    }

    public List<Client> getAllClients() {
        return clientDao.selectAllClient();
    }

    public Optional<Client> getClientById(UUID id) {
        return clientDao.selectClientById(id);
    }

    public int deleteClient(UUID id) {
        return clientDao.deleteClientById(id);
    }

    public int updateClient(UUID id, Client newClient) {
        return clientDao.updateClientById(id, newClient);
    }
}
