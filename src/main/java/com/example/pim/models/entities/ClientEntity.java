package com.example.pim.models.entities;

import com.example.pim.models.enums.ClientEnums.SectorEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class ClientEntity {
    @Id
    UUID id;
    @Column(nullable = false, length = 150)
    String name;
    @Column(unique = true, nullable = false, length = 200)
    String email;
    @Column(nullable = false)
    String password;
    @Column
    Integer number;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    SectorEnum sector;

    public ClientEntity() {
    }

    public ClientEntity(UUID id, String name, String email, String password, Integer number, SectorEnum sector) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.sector = sector;
    }
}
