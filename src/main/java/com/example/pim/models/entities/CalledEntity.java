package com.example.pim.models.entities;

import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;
import com.example.pim.models.enums.CalledEnums.CalledType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CalledEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_called",unique = true, nullable = false)
    UUID id;
    @Column(name = "descripition_called", nullable = false, length = 1000)
    String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_called", nullable = false)
    CalledStatusEnum statusCalled;
    @Column(name = "data_abertura_chamado", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataAberturaChamado;
    @Column(name = "data_fechamento_chamado", nullable = true)
    private LocalDateTime dataFechamentoChamado;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_called", nullable = false)
    CalledType type;

    public CalledEntity() {
    }

    public CalledEntity(UUID id, String description, CalledStatusEnum statusCalled, LocalDateTime dataAberturaChamado, LocalDateTime dataFechamentoChamado, CalledType type) {
        this.id = id;
        this.description = description;
        this.statusCalled = statusCalled;
        this.dataAberturaChamado = dataAberturaChamado;
        this.dataFechamentoChamado = dataFechamentoChamado;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalledStatusEnum getStatusCalled() {
        return statusCalled;
    }

    public void setStatusCalled(CalledStatusEnum statusCalled) {
        this.statusCalled = statusCalled;
    }

    public LocalDateTime getDataAberturaChamado() {
        return dataAberturaChamado;
    }

    public void setDataAberturaChamado(LocalDateTime dataAberturaChamado) {
        this.dataAberturaChamado = dataAberturaChamado;
    }

    public LocalDateTime getDataFechamentoChamado() {
        return dataFechamentoChamado;
    }

    public void setDataFechamentoChamado(LocalDateTime dataFechamentoChamado) {
        this.dataFechamentoChamado = dataFechamentoChamado;
    }

    public CalledType getType() {
        return type;
    }

    public void setType(CalledType type) {
        this.type = type;
    }
}
