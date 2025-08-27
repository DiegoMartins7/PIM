package com.example.pim.models.dtos.TecDtos;

import com.example.pim.models.enums.TecEnums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;

import java.util.UUID;

public record TecDto(UUID id, String email, String name, String password, PermissionEnum permission, TecLevelEnum tecLevel) {
}
