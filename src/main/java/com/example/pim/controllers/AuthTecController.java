package com.example.pim.controllers;

import com.example.pim.configurations.JwtUtil;
import com.example.pim.models.dtos.TecDtos.AuthTecDto;
import com.example.pim.models.dtos.TecDtos.AuthTecResponseDto;
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
public class AuthTecController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthTecController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTecResponseDto> login(@RequestBody AuthTecDto authTecDto) {
        Optional<TecEntity> authenticatedTec = authService.authenticateTec(authTecDto);

        if (authenticatedTec.isPresent()) {
            TecEntity tec = authenticatedTec.get();

            String token = jwtUtil.gererateToken(tec.getEmail(), tec.getPermission().name());

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(new AuthTecResponseDto("Login realizado com sucesso", tec.getPermission()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthTecResponseDto("Credenciais inválidas", null));
        }
    }
}