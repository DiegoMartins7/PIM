package com.example.pim.models.entities;

import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;
import com.example.pim.models.enums.CalledEnums.CalledType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "called")
public class CalledEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_called",unique = true, nullable = false)
    private UUID id;
    @Column(name = "descripition_called", nullable = false, length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_called", nullable = false)
    private CalledStatusEnum statusCalled;
    @Column(name = "opening_date_call", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime openingDateCall;
    @Column(name = "closing_date_call", nullable = true)
    private LocalDateTime closingDateCall;
    @Column(name = "scheduling_date_call", nullable = true)
    private LocalDateTime callSchedulingDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_called", nullable = false)
    CalledType typeCall;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tec_id", nullable = false)
    private TecEntity tec;
    @OneToMany(mappedBy = "called", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerTheCallEntity> answers = new ArrayList<>();


    public CalledEntity() {
        this.openingDateCall = LocalDateTime.now();
        this.statusCalled = CalledStatusEnum.OPEN;
    }

    // Adicionar resposta
    public void addAnswer(AnswerTheCallEntity answer) {
        answers.add(answer);
        answer.setCalled(this);
        this.statusCalled = CalledStatusEnum.IN_PROGRESS;
    }

    public void closeCall() {
        this.closingDateCall = LocalDateTime.now();
        this.statusCalled = CalledStatusEnum.FINISHED;
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

    public LocalDateTime getOpeningDateCall() {
        return openingDateCall;
    }

    public void setOpeningDateCall(LocalDateTime openingDateCall) {
        this.openingDateCall = openingDateCall;
    }

    public LocalDateTime getClosingDateCall() {
        return closingDateCall;
    }

    public void setClosingDateCall(LocalDateTime closingDateCall) {
        this.closingDateCall = closingDateCall;
    }

    public LocalDateTime getCallSchedulingDate() {
        return callSchedulingDate;
    }

    public void setCallSchedulingDate(LocalDateTime callSchedulingDate) {
        this.callSchedulingDate = callSchedulingDate;
    }

    public CalledType getTypeCall() {
        return typeCall;
    }

    public void setTypeCall(CalledType typeCall) {
        this.typeCall = typeCall;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public TecEntity getTec() {
        return tec;
    }

    public void setTec(TecEntity tec) {
        this.tec = tec;
    }

    public List<AnswerTheCallEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerTheCallEntity> answers) {
        this.answers = answers;
    }
}
