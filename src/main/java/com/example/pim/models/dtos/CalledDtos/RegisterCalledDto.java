package com.example.pim.models.dtos.CalledDtos;

import com.example.pim.models.enums.CalledEnums.CalledType;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterCalledDto(UUID clientId, UUID tecId, String description, CalledType typeCall, LocalDateTime callSchedulingDate) {
}
