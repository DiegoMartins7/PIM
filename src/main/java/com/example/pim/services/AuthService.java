package com.example.pim.services;

import com.example.pim.models.dtos.TecDtos.AuthTecDto;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.repositorys.TecRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final TecRepository tecRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(TecRepository tecRepository, PasswordEncoder passwordEncoder) {
        this.tecRepository = tecRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<TecEntity> authenticateTec(AuthTecDto authTecDto) {
        return tecRepository.findByEmail(authTecDto.email())
                .filter(tec -> passwordEncoder.matches(authTecDto.password(), tec.getPassword()));
    }
}
