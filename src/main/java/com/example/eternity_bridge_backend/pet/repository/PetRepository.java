package com.example.eternity_bridge_backend.pet.repository;

import com.example.eternity_bridge_backend.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long>, PetRepositoryQuerydsl {
}
