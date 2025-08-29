package com.example.pim.repositorys;

import com.example.pim.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    Optional<ClientEntity> findByNameIgnoreCase(String name);

    Optional<ClientEntity> findByEmail(String email);
}
