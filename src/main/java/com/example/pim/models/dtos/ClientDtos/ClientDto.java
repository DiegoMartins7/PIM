package com.example.pim.models.dtos.ClientDtos;

import com.example.pim.models.enums.ClientEnums.SectorEnum;

import java.util.UUID;

public record ClientDto(UUID id, String name, String email, String password, Integer number, SectorEnum sector) {
}
