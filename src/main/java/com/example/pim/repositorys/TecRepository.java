package com.example.pim.repositorys;

import com.example.pim.models.entities.TecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TecRepository extends JpaRepository<TecEntity, UUID> {

    Optional<TecEntity> findByName(String name);

    Optional<TecEntity> findByEmail(String email);
}