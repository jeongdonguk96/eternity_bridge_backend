package com.example.eternity_bridge_backend.pet.repository;

import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.dto.GetPetsResponse;

import java.util.List;

public interface PetRepositoryQuerydsl {
    List<GetPetsResponse> findMyPets(Long memberId);
    boolean checkDuplicatedPet(CreatePetRequest request, Long memberId);
}
