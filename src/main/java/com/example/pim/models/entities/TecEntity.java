package com.example.pim.models.entities;

import com.example.pim.models.enums.TecEnums.PermissionEnum;
import com.example.pim.models.enums.TecEnums.TecLevelEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
public class TecEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissionEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionEnum permission) {
        this.permission = permission;
    }

    public TecLevelEnum getTecLevel() {
        return tecLevel;
    }

    public void setTecLevel(TecLevelEnum tecLevel) {
        this.tecLevel = tecLevel;
    }
}
