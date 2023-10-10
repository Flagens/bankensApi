package com.example.demo.controller;

import com.example.demo.model.*;



import com.example.demo.service.ClientService;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;



    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        List<Client> clients = clientService.findAllClients();

        if(clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ClientDTO> clientDTOS = clients.stream().map(clientService::mapEntityToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientDTOS);
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClients(@PathVariable Long id) {
            if(!clientService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            Client client = clientService.findClientsById(id);
            ClientDTO clientDTO = clientService.mapEntityToDTO(client);
            return ResponseEntity.accepted().body(clientDTO);
        }


    @PostMapping("/createClients")
    public ResponseEntity<Client> createClients(@RequestBody ClientDTO clientDTO) {

        Client client = clientService.mapDTOToEntity(clientDTO);
        client = clientService.createClient(client);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(client);
    }

    @PutMapping("/updateClients/{id}")
    public ResponseEntity<Object> updateClients(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {

            if (!clientService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            Client client = clientService.mapDTOToEntity(clientDTO);
            client.setClient_id(id);

            Client updatedClient = clientService.updateClient(client);

            ClientDTO updatedDTO = clientService.mapEntityToDTO(updatedClient);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updatedDTO);
        }



    @DeleteMapping("/deleteClients/{id}")
    public ResponseEntity<Object> deleteClients(@PathVariable Long id) {


            if (!clientService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        }
}



