package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.ClientDTO;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientDTOS = clientService.getAllClientDTOs();
        if (clientDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDTOS);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.getClientDTO(id);
        if (clientDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body(clientDTO);
    }

    @PostMapping("/createClients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientService.createClientFromDTO(clientDTO);
        clientService.process(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/updateClients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        if (!clientService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ClientDTO updatedDTO = clientService.updateClientFromDTO(id, clientDTO);
        Client updatedClient = clientService.findClientById(id);
        clientService.process(updatedClient);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedDTO);
    }

    @DeleteMapping("/deleteClients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        if (!clientService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
