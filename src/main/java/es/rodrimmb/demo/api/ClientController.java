package es.rodrimmb.demo.api;

import es.rodrimmb.demo.model.Client;
import es.rodrimmb.demo.service.ClientService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/client")
@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void addClient(@Valid @NonNull @RequestBody Client client) {
        clientService.addClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable("id") UUID id) {
        return clientService.getClientById(id)
                .orElse(null);
    }

    @DeleteMapping("{id}")
    public void deleteClientById(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
    }

    @PutMapping("{id}")
    public void updateClient(@PathVariable("id") UUID id,
                             @Valid @NonNull @RequestBody Client clientToUpdate) {
        clientService.updateClient(id, clientToUpdate);
    }
}
