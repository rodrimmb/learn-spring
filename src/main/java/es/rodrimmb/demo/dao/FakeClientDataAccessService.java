package es.rodrimmb.demo.dao;

import es.rodrimmb.demo.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeClientDataAccessService implements ClientDao {

    private static List<Client> DB = new ArrayList<>();

    @Override
    public int insertClient(UUID id, Client client) {
        DB.add(Client.builder().id(id).name(client.getName()).build());
        return 1;
    }

    @Override
    public Optional<Client> selectClientById(UUID id) {
        return DB.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Client> selectAllClient() {
        return DB;
    }

    @Override
    public int deleteClientById(UUID id) {
        Optional<Client> clientMaybe = selectClientById(id);
        if(clientMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(clientMaybe.get());
        return 1;
    }

    @Override
    public int updateClientById(UUID id, Client clientToUpdate) {
        return selectClientById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if(indexOfUserToUpdate >= 0) {
                        DB.set(
                                indexOfUserToUpdate,
                                Client.builder().id(id).name(clientToUpdate.getName()).build()
                        );
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
