package com.example.pim.models.dtos.TecDtos;

import com.example.pim.models.enums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;

public record UpdateTecDto(String name, TecLevelEnum level, PermissionEnum permission) {
}
