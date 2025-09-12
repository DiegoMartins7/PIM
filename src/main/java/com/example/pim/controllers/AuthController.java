package com.example.pim.controllers;

import com.example.pim.configurations.JwtUtil;
import com.example.pim.models.dtos.ClientDtos.AuthClientDto;
import com.example.pim.models.dtos.ClientDtos.AuthClientResponseDto;
import com.example.pim.models.dtos.TecDtos.AuthTecDto;
import com.example.pim.models.dtos.TecDtos.AuthTecResponseDto;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/tec")
    public ResponseEntity<AuthTecResponseDto> loginTec(@RequestBody AuthTecDto authTecDto) {
        AuthTecResponseDto response = authService.loginTec(authTecDto);

        if (response.token() != null) {
            AuthTecResponseDto bodyResponse = new AuthTecResponseDto(
                    response.message(),
                    response.permission(),
                    null
            );

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + response.token()) // token no header
                    .body(bodyResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/login/client")
    public ResponseEntity<AuthClientResponseDto> loginClient(@RequestBody AuthClientDto authClientDto) {
        AuthClientResponseDto response = authService.loginClient(authClientDto);

        if (response.token() != null) {
            AuthClientResponseDto bodyResponse = new AuthClientResponseDto(
                    response.message(),
                    response.sector(),
                    null
            );

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + response.token()) // token no header
                    .body(bodyResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}