package com.example.pim.models.dtos.TecDtos;

import com.example.pim.models.enums.PermissionEnum;

public record AuthTecResponseDto(String message, PermissionEnum permission, String token) {
}
