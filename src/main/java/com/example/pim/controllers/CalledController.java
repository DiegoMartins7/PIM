package com.example.pim.controllers;

import com.example.pim.models.dtos.CalledDtos.RegisterCalledDto;
import com.example.pim.models.dtos.CalledDtos.UpdateCalledDto;
import com.example.pim.models.entities.AnswerTheCallEntity;
import com.example.pim.models.entities.CalledEntity;
import com.example.pim.services.CalledService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/called")
public class CalledController {

    private final CalledService calledService;

    public CalledController(CalledService calledService) {
        this.calledService = calledService;
    }

    //Criar chamado
    @PostMapping("/register")
    public ResponseEntity<CalledEntity> registerCalled(@RequestBody RegisterCalledDto dto) {
        CalledEntity called = calledService.registerCalled(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(called);
    }

    //Atualizar chamado e adicionar resposta do técnico
    @PatchMapping("/update")
    public ResponseEntity<CalledEntity> updateCalled(@RequestBody UpdateCalledDto dto) {
        CalledEntity updated = calledService.updateCalled(dto);
        return ResponseEntity.ok(updated);
    }

    //Buscar chamados de um cliente pelo nome
    @GetMapping("/consultation/client/{name}")
    public ResponseEntity<List<CalledEntity>> getCallsByClientName(@PathVariable String name) {
        List<CalledEntity> calls = calledService.getCallsByClientName(name);
        return ResponseEntity.ok(calls);
    }

    //Buscar chamados de um técnico pelo nome
    @GetMapping("/consultation/tec/{name}")
    public ResponseEntity<List<CalledEntity>> getCallsByTecName(@PathVariable String name) {
        List<CalledEntity> calls = calledService.getCallsByTecName(name);
        return ResponseEntity.ok(calls);
    }

    //Buscar chamado específico pelo ID
    @GetMapping("/consultation/{id}")
    public ResponseEntity<CalledEntity> getCalledById(@PathVariable UUID id) {
        CalledEntity called = calledService.getCalledById(id);
        return ResponseEntity.ok(called);
    }

    //Listar respostas de um chamado
    @GetMapping("/consultation/answers/{id}")
    public ResponseEntity<List<AnswerTheCallEntity>> getAnswers(@PathVariable UUID id) {
        List<AnswerTheCallEntity> answers = calledService.getAnswers(id);
        return ResponseEntity.ok(answers);
    }
}

