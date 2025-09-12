package com.example.pim.models.dtos.CalledDtos;

import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateCalledDto(UUID calledId, UUID tecId, String description, CalledStatusEnum calledStatus, LocalDateTime callSchedulingDate, String answerDescription) {
}
