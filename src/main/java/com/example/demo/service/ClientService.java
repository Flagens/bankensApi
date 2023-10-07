package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.AccountDTO;
import com.example.demo.model.Client;
import com.example.demo.model.ClientDTO;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService  {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientsById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {return clientRepository.existsById(id);}

    public ClientDTO mapEntityToDTO(Client entity) {
        return modelMapper.map(entity, ClientDTO.class);
    }

    public Client mapDTOToEntity(ClientDTO dto) {
        return modelMapper.map(dto, Client.class);
    }



}
