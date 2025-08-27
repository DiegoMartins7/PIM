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

@RestController
@RequestMapping("/tec")
public class TecController {

    private final TecService tecService;

    public TecController(TecService tecService) {
        this.tecService = tecService;
    }

    //Criar Tec
    @PostMapping("/register")
    public ResponseEntity<TecResponseDto> createTec(@RequestBody TecDto tecDto){
        var tec = tecService.registerTec(tecDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TecResponseDto(tec.getId(), tec.getEmail(), tec.getPermission(), tec.getTecLevel()));
    }

    //Alterar Tec
    @PutMapping("/update/{id}")
    public ResponseEntity<TecResponseDto> updateTec(@PathVariable String id, @RequestBody UpdateTecDto updateDto) {
        var updatedTec = tecService.updateTec(id, updateDto);
        return ResponseEntity.ok(updatedTec);
    }

    @DeleteMapping("/delete/{idtec}")
    public ResponseEntity<MessageResponseDto> deleteTec(@PathVariable String idtec) {
        try {
            tecService.deleteTec(idtec);
            return ResponseEntity.ok(new MessageResponseDto("Usuário Deletado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponseDto(e.getMessage()));
        }
    }

    //Buscar todos os Tecs
    @GetMapping("/consulta/all")
    public ResponseEntity<List<TecResponseDto>> getAllTec() {
        List<TecResponseDto> tec = tecService.getAllTec();
        return ResponseEntity.ok(tec);
    }

    //Buscar Tec
    @GetMapping("/consulta/{name}")
    public ResponseEntity<TecResponseDto> findByName(@PathVariable String name) {
        var tec = tecService.findByName(name);

        if (tec != null) {
            return ResponseEntity.ok(
                    new TecResponseDto(tec.getId(), tec.getName(), tec.getPermission(), tec.getTecLevel())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
