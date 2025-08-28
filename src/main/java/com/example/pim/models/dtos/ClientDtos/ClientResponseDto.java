package com.example.pim.models.dtos.ClientDtos;

import com.example.pim.models.enums.ClientEnums.SectorEnum;

import java.util.UUID;

public record ClientResponseDto(UUID id, String name, SectorEnum sector, Integer number) {
}
