package com.example.pim.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "answer_the_call")
public class AnswerTheCallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "called_id", nullable = false)
    private CalledEntity called;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tec_id", nullable = false)
    private TecEntity tec;
    @Column(name = "answer_date", nullable = false)
    private LocalDateTime answerDate;
    @Column(name = "description", length = 1000)
    private String description;

    public AnswerTheCallEntity() {
        this.answerDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CalledEntity getCalled() {
        return called;
    }

    public void setCalled(CalledEntity called) {
        this.called = called;
    }

    public TecEntity getTec() {
        return tec;
    }

    public void setTec(TecEntity tec) {
        this.tec = tec;
    }

    public LocalDateTime getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDateTime answerDate) {
        this.answerDate = answerDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
