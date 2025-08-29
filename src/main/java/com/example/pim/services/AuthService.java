package com.example.pim.services;

import com.example.pim.models.dtos.ClientDtos.AuthClientDto;
import com.example.pim.models.dtos.TecDtos.AuthTecDto;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.repositorys.ClientRepository;
import com.example.pim.repositorys.TecRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final TecRepository tecRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;

    public AuthService(TecRepository tecRepository, PasswordEncoder passwordEncoder, ClientRepository clientRepository) {
        this.tecRepository = tecRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    public Optional<TecEntity> authenticateTec(AuthTecDto authTecDto) {
        return tecRepository.findByEmail(authTecDto.email())
                .filter(tec -> passwordEncoder.matches(authTecDto.password(), tec.getPassword()));
    }

    public Optional<ClientEntity> authenticateClient(AuthClientDto authClientDto) {
        return clientRepository.findByEmail(authClientDto.email())
                .filter(client -> passwordEncoder.matches(authClientDto.password(), client.getPassword()));
    }
}