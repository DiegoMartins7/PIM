package com.example.pim.services;

import com.example.pim.models.dtos.CalledDtos.RegisterCalledDto;
import com.example.pim.models.dtos.CalledDtos.UpdateCalledDto;
import com.example.pim.models.entities.AnswerTheCallEntity;
import com.example.pim.models.entities.CalledEntity;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import com.example.pim.models.enums.CalledEnums.CalledStatusEnum;
import com.example.pim.repositorys.AnswerRepository;
import com.example.pim.repositorys.CalledRepository;
import com.example.pim.repositorys.ClientRepository;
import com.example.pim.repositorys.TecRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CalledService {

    private final CalledRepository calledRepository;
    private final AnswerRepository answerRepository;
    private final TecRepository tecRepository;
    private final ClientRepository clientRepository;

    public CalledService(CalledRepository calledRepository, AnswerRepository answerRepository,
                         TecRepository tecRepository,
                         ClientRepository clientRepository) {
        this.calledRepository = calledRepository;
        this.answerRepository = answerRepository;
        this.tecRepository = tecRepository;
        this.clientRepository = clientRepository;
    }

    // Criar chamado
    public CalledEntity registerCalled(RegisterCalledDto dto) {
        ClientEntity client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        TecEntity tec = tecRepository.findById(dto.tecId())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        CalledEntity called = new CalledEntity();
        called.setClient(client);
        called.setTec(tec);
        called.setDescription(dto.description());
        called.setTypeCall(dto.typeCall());

        if (dto.callSchedulingDate() != null) {
            called.setCallSchedulingDate(dto.callSchedulingDate());
        }

        return calledRepository.save(called);
    }

    public CalledEntity updateCalled(UpdateCalledDto dto) {
        CalledEntity called = calledRepository.findById(dto.calledId())
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        TecEntity tec = tecRepository.findById(dto.tecId())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));

        if (dto.description() != null) {
            called.setDescription(dto.description());
        }
        if (dto.calledStatus() != null) {
            called.setStatusCalled(dto.calledStatus());
            if (dto.calledStatus() == CalledStatusEnum.FINISHED) {
                called.setClosingDateCall(LocalDateTime.now());
            }
        }
        if (dto.callSchedulingDate() != null) {
            called.setCallSchedulingDate(dto.callSchedulingDate());
        }

        if (dto.answerDescription() != null && !dto.answerDescription().isBlank()) {
            AnswerTheCallEntity answer = new AnswerTheCallEntity();
            answer.setCalled(called);
            answer.setTec(tec);
            answer.setDescription(dto.answerDescription());
            called.addAnswer(answer);
            answerRepository.save(answer);
        }

        return calledRepository.save(called);
    }


    public List<AnswerTheCallEntity> getAnswers(UUID calledId) {
        CalledEntity called = calledRepository.findById(calledId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        return called.getAnswers();
    }

    public CalledEntity getCalledById(UUID calledId) {
        return calledRepository.findById(calledId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
    }

    // Buscar chamados pelo nome do cliente
    public List<CalledEntity> getCallsByClientName(String clientName) {
        return calledRepository.findByClient_NameIgnoreCase(clientName);
    }

    // Buscar chamados pelo nome do técnico
    public List<CalledEntity> getCallsByTecName(String tecName) {
        return calledRepository.findByTec_NameIgnoreCase(tecName);
    }
}