package com.example.eternity_bridge_backend.pet.service;

import com.example.eternity_bridge_backend.exception.code.PetErrorCode;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.dto.GetPetsResponse;
import com.example.eternity_bridge_backend.pet.entity.Pet;
import com.example.eternity_bridge_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.eternity_bridge_backend.exception.code.PetErrorCode.DUPLICATED_PET;


@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;


    // 강아지를 등록한다.
    @Transactional
    public void createPet(CreatePetRequest request, Long memberId) {
        checkDuplicatedPet(request, memberId);
        petRepository.save(request.from(memberId));
    }


    // 강아지를 조회한다.
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new CommonException(PetErrorCode.PET_NOT_FOUND));
    }


    // 사용자의 강아지를 조회한다.
    public List<GetPetsResponse> getMyPets(Long memberId) {
        return Optional.ofNullable(petRepository.findMyPets(memberId))
                .filter(pets -> !pets.isEmpty())
                .orElseThrow(() -> new CommonException(PetErrorCode.MEMBER_HAS_NO_PETS));
    }


    // 강아지 등록 시 중복체크를 진행한다.
    private void checkDuplicatedPet(CreatePetRequest request, Long memberId) {
        if (petRepository.checkDuplicatedPet(request, memberId)) {
            throw new CommonException(DUPLICATED_PET);
        }
    }

}
