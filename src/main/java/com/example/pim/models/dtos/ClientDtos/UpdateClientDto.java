package com.example.pim.models.dtos.ClientDtos;

import com.example.pim.models.enums.ClientEnums.SectorEnum;

public record UpdateClientDto(String name, SectorEnum sector, Integer number) {
}
