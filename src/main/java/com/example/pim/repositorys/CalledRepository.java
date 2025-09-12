package com.example.pim.repositorys;

import com.example.pim.models.entities.CalledEntity;
import com.example.pim.models.entities.ClientEntity;
import com.example.pim.models.entities.TecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CalledRepository extends JpaRepository<CalledEntity, UUID> {

    // Buscar todos os chamados de um cliente pelo objeto
    List<CalledEntity> findByClient(ClientEntity client);

    // Buscar todos os chamados de um cliente pelo ID
    List<CalledEntity> findByClientId(UUID clientId);

    // Buscar todos os chamados de um cliente pelo nome
    List<CalledEntity> findByClient_NameIgnoreCase(String name);

    // Buscar todos os chamados de um técnico pelo objeto
    List<CalledEntity> findByTec(TecEntity tec);

    // Buscar todos os chamados de um técnico pelo nome
    List<CalledEntity> findByTec_NameIgnoreCase(String name);
}
