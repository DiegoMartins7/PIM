package com.example.pim.controllers;

import com.example.pim.models.dtos.MessageResponseDto;
import com.example.pim.models.dtos.TecDtos.TecDto;
import com.example.pim.models.dtos.TecDtos.TecResponseDto;
import com.example.pim.models.dtos.TecDtos.UpdateTecDto;
import com.example.pim.services.TecService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tec")
public class TecController {

    private final TecService tecService;

    public TecController(TecService tecService) {
        this.tecService = tecService;
    }

    //Criar Tecnico
    @PostMapping("/register")
    public ResponseEntity<TecResponseDto> registerTec(@RequestBody TecDto tecDto){
        var tec = tecService.registerTec(tecDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TecResponseDto(tec.getId(), tec.getName(), tec.getEmail(), tec.getPermission(), tec.getTecLevel()));
    }

    //Alterar Tecnico
    @PutMapping("/update/{id}")
    public ResponseEntity<TecResponseDto> updateTec(@PathVariable UUID id, @RequestBody UpdateTecDto updateDto) {
        var updatedTec = tecService.updateTec(id, updateDto);
        return ResponseEntity.ok(updatedTec);
    }

    //Deletar Tecnico
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponseDto> deleteTec(@PathVariable UUID id) {
        try {
            tecService.deleteTec(id);
            return ResponseEntity.ok(new MessageResponseDto("Tecnico Deletado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponseDto(e.getMessage()));
        }
    }

    //Buscar todos os Tecnicos
    @GetMapping("/consulta/all")
    public ResponseEntity<List<TecResponseDto>> getAllTec() {
        List<TecResponseDto> tec = tecService.getAllTec();
        return ResponseEntity.ok(tec);
    }

    //Buscar Tecnico por nome
    @GetMapping("/consulta/{name}")
    public ResponseEntity<TecResponseDto> findByName(@PathVariable String name) {
        var tec = tecService.findByName(name);

        if (tec != null) {
            return ResponseEntity.ok(
                    new TecResponseDto(tec.getId(), tec.getName(), tec.getName(), tec.getPermission(), tec.getTecLevel())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
