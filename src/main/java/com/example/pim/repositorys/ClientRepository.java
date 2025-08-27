package com.example.pim.repositorys;

import com.example.pim.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByName(String name);

}
