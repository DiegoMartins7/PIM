package com.example.pim.models.dtos.TecDtos;

import com.example.pim.models.enums.TecEnums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;

import java.util.UUID;

public record TecResponseDto(UUID id, String name, PermissionEnum permission, TecLevelEnum tecLevel) {
}
