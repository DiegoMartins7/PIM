package com.example.pim.services;

import com.example.pim.configurations.JwtUtil;
import com.example.pim.models.dtos.ClientDtos.AuthClientDto;
import com.example.pim.models.dtos.ClientDtos.AuthClientResponseDto;
import com.example.pim.models.dtos.TecDtos.AuthTecDto;
import com.example.pim.models.dtos.TecDtos.AuthTecResponseDto;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.repositorys.ClientRepository;
import com.example.pim.repositorys.TecRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final TecRepository tecRepository;
    private final ClientRepository clientRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(TecRepository tecRepository, ClientRepository clientRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.tecRepository = tecRepository;
        this.clientRepository = clientRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthTecResponseDto loginTec(AuthTecDto authTecDto) {
        try {
            // Autenticação pelo Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authTecDto.email(), authTecDto.password())
            );

            // Se passar, busca o técnico
            TecEntity tec = tecRepository.findByEmail(authTecDto.email())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

            // Gera JWT
            String token = jwtUtil.gererateToken(tec.getEmail(), tec.getPermission().name());

            return new AuthTecResponseDto("Login realizado com sucesso", tec.getPermission(), token);

        } catch (Exception e) {
            return new AuthTecResponseDto("Credenciais inválidas", null, null);
        }
    }

    // Login de cliente
    public AuthClientResponseDto loginClient(AuthClientDto authClientDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authClientDto.email(), authClientDto.password())
            );

            ClientEntity client = clientRepository.findByEmail(authClientDto.email())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            String token = jwtUtil.gererateToken(client.getEmail(), client.getSector().name());

            return new AuthClientResponseDto("Login realizado com sucesso", client.getSector().name(), token);

        } catch (Exception e) {
            return new AuthClientResponseDto("Credenciais inválidas", null, null);
        }
    }
}