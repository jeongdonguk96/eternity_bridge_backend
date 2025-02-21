package com.example.eternity_bridge_backend.pet.service;

import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.entity.Pet;
import com.example.eternity_bridge_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;


    // 강아지를 등록한다.
    @Transactional
    public Pet createPet(CreatePetRequest request, Long memberId) {
        checkDuplicatedPet(request, memberId);
        Pet pet = petRepository.save(request.from(memberId));
        imageService.modifyDomainId(pet);

        return pet;
    }

}
