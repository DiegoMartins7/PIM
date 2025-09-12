package com.example.pim.models.dtos.CalledDtos;

import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;
import com.example.pim.models.enums.CalledEnums.CalledType;

import java.time.LocalDateTime;

public record CalledResponseDto(String description, CalledStatusEnum statusCalled, LocalDateTime openingDateCall, LocalDateTime closingDateCall, LocalDateTime callSchedulingDate, CalledType typeCall) {
}
