package com.example.pim.controllers;

import com.example.pim.models.dtos.ClientDtos.ClientDto;
import com.example.pim.models.dtos.ClientDtos.ClientResponseDto;
import com.example.pim.models.dtos.ClientDtos.UpdateClientDto;
import com.example.pim.models.dtos.MessageResponseDto;
import com.example.pim.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //Criar Cliente
    @PostMapping("/register")
    public ResponseEntity<ClientResponseDto> registerClient(@RequestBody ClientDto clientDto){
        var client = clientService.registerClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ClientResponseDto(client.getId(), client.getEmail(), client.getSector(), client.getNumber()));
    }

    //Alterar Cliente
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable UUID id, @RequestBody UpdateClientDto updateClientDto) {
        var updateClient = clientService.updateClient(id, updateClientDto);
        return ResponseEntity.ok(updateClient);
    }

    //Deletar Cliente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponseDto> deleteClient(@PathVariable UUID id){
        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok(new MessageResponseDto("Cliente Deletado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponseDto(e.getMessage()));
        }
    }

    //Buscar Clientes por nome
    @GetMapping("/consultation/{name}")
    public ResponseEntity<ClientResponseDto> findByName(@PathVariable String name) {
        var client = clientService.findByName(name);

        if (client != null) {
            return ResponseEntity.ok(
                    new ClientResponseDto(client.getId(), client.getName(), client.getSector(), client.getNumber())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Buscar Clientes por id
    @GetMapping("/consultation/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable UUID id) {
        var client = clientService.findById(id);

        if (client != null) {
            return ResponseEntity.ok(
                    new ClientResponseDto(client.getId(), client.getName(), client.getSector(), client.getNumber())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Buscar todos os Clientes
    @GetMapping("/consultation/all")
    public ResponseEntity<List<ClientResponseDto>> getAllClient() {
        List<ClientResponseDto> client = clientService.getAllClient();
        return ResponseEntity.ok(client);
    }
}
