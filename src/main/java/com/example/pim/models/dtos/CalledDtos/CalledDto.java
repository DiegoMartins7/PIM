package com.example.pim.models.dtos.CalledDtos;

import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;
import com.example.pim.models.enums.CalledEnums.CalledType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalledDto(UUID id, String description, CalledStatusEnum statusCalled, LocalDateTime openingDateCall, LocalDateTime closingDateCall, LocalDateTime callSchedulingDate, CalledType typeCall) {
}