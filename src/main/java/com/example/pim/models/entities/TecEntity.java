package com.example.pim.models.entities;

import com.example.pim.models.enums.TecEnums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class TecEntity {
    @Id
    UUID id;
    @Column(nullable = false, length = 150)
    String name;
    @Column(unique = true, nullable = false, length = 200)
    String email;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PermissionEnum permission;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TecLevelEnum tecLevel;

    public TecEntity() {
    }

    public TecEntity(UUID id, String email, String password, PermissionEnum permission, TecLevelEnum tecLevel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.permission = permission;
        this.tecLevel = tecLevel;
    }
}
