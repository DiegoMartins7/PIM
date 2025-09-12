package com.example.pim.services;

import com.example.pim.models.dtos.ClientDtos.ClientDto;
import com.example.pim.models.dtos.ClientDtos.ClientResponseDto;
import com.example.pim.models.dtos.ClientDtos.UpdateClientDto;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.enums.ClientEnums.SectorEnum;
import com.example.pim.repositorys.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Cadastrar um Cliente
    public ClientEntity registerClient(ClientDto dto) {
        ClientEntity client = new ClientEntity();
        client.setEmail(dto.email());
        client.setPassword(passwordEncoder.encode(dto.password()));
        client.setName(dto.name());
        client.setNumber(dto.number());
        client.setSector(SectorEnum.COMMERCIAL);

        return clientRepository.save(client);
    }

    //Alterar um Cliente
    public ClientResponseDto updateClient(UUID id, UpdateClientDto dto) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        client.setName(dto.name());
        client.setSector(dto.sector());
        client.setNumber(dto.number());

        clientRepository.save(client);

        return new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getSector(),
                client.getNumber()
        );
    }

    //Deletar um Cliente
    @Transactional
    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        clientRepository.deleteById(id);
    }

    //Procurar Cliente por nome
    public ClientEntity findByName(String name) {
        return clientRepository.findByNameIgnoreCase(name).orElse(null);
    }

    //Procurar Cliente por id
    public ClientEntity findById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    //Procurar todos o clientes
    public List<ClientResponseDto> getAllClient() {
        List<ClientEntity> client = clientRepository.findAll();
        List<ClientResponseDto> response = new ArrayList<>();

        for (ClientEntity client1 : client) {
            ClientResponseDto dto = new ClientResponseDto(client1.getId(), client1.getName(), client1.getSector(), client1.getNumber());
            response.add(dto);
        }
        return response;
    }
}
