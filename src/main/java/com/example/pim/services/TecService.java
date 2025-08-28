package com.example.pim.services;

import com.example.pim.models.dtos.TecDtos.TecDto;
import com.example.pim.models.dtos.TecDtos.TecResponseDto;
import com.example.pim.models.dtos.TecDtos.UpdateTecDto;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.models.enums.TecEnums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;
import com.example.pim.repositorys.TecRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TecService {

    private final TecRepository tecRepository;
    private final PasswordEncoder passwordEncoder;

    public TecService(TecRepository tecRepository, PasswordEncoder passwordEncoder) {
        this.tecRepository = tecRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TecEntity registerTec(TecDto dto) {
        TecEntity tec = new TecEntity();
        tec.setEmail(dto.email());
        tec.setPassword(passwordEncoder.encode(dto.password()));
        tec.setName(dto.name());
        tec.setPermission(PermissionEnum.TEC);
        tec.setTecLevel(TecLevelEnum.NORMAL);

        return tecRepository.save(tec);
    }

    public TecResponseDto updateTec(UUID id, UpdateTecDto dto) {
        var tec = tecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado!"));

        tec.setName(dto.name());
        tec.setTecLevel(dto.level());
        tec.setPermission(dto.permission());

        tecRepository.save(tec);

        return new TecResponseDto(
                tec.getId(),
                tec.getName(),
                tec.getEmail(),
                tec.getPermission(),
                tec.getTecLevel()
        );
    }

    @Transactional
    public void deleteTec(UUID id) {
        if (!tecRepository.existsById(id)) {
            throw new RuntimeException("Tecnico não encontrado!");
        }
        tecRepository.deleteById(id);
    }

    public TecEntity findByName(String name) {
        return tecRepository.findByName(name).orElse(null);
    }

    public List<TecResponseDto> getAllTec() {
        List<TecEntity> tec = tecRepository.findAll();
        List<TecResponseDto> response = new ArrayList<>();

        for (TecEntity tec1 : tec) {
            TecResponseDto dto = new TecResponseDto(tec1.getId(), tec1.getName(), tec1.getEmail(), tec1.getPermission(), tec1.getTecLevel());
            response.add(dto);
        }
        return response;
    }
}
