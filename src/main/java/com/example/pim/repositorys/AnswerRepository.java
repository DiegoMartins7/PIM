package com.example.pim.repositorys;

import com.example.pim.models.entities.AnswerTheCallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerTheCallEntity, UUID> {
}
