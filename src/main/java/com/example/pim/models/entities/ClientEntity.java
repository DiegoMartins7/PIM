package com.example.pim.models.entities;

import com.example.pim.models.enums.ClientEnums.SectorEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Entity
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(name = "name_client", nullable = false, length = 150)
    String name;
    @Column(name = "email_client", unique = true, nullable = false, length = 200)
    String email;
    @Column(name = "passaword_client", nullable = false)
    String password;
    @Column(name = "number_client")
    Integer number;
    @Enumerated(EnumType.STRING)
    @Column(name = "sector_client", nullable = false)
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SectorEnum getSector() {
        return sector;
    }

    public void setSector(SectorEnum sector) {
        this.sector = sector;
    }
}
