package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.ClientDTO;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService extends BaseService<Client> {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientDTO getClientDTO(Long id) {
        Client client = findClientById(id);
        if (client == null) {
            return null;
        }
        return mapEntityToDTO(client);
    }

    public List<ClientDTO> getAllClientDTOs() {
        return findAllClients().stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public Client createClientFromDTO(ClientDTO clientDTO) {
        Client client = mapDTOToEntity(clientDTO);
        return createClient(client);
    }

    public ClientDTO updateClientFromDTO(Long id, ClientDTO clientDTO) {
        Client client = mapDTOToEntity(clientDTO);
        client.setClient_id(id);
        Client updatedClient = updateClient(client);
        return mapEntityToDTO(updatedClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    public ClientDTO mapEntityToDTO(Client entity) {
        return modelMapper.map(entity, ClientDTO.class);
    }

    public Client mapDTOToEntity(ClientDTO dto) {
        return modelMapper.map(dto, Client.class);
    }

    @Override
    public void process(Client client) {

        System.out.println("elo1 " + client.getName());
    }
}
