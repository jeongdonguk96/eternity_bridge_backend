package com.example.eternity_bridge_backend.pet.service;

import com.example.eternity_bridge_backend.exception.code.PetErrorCode;
import com.example.eternity_bridge_backend.exception.exception.CommonException;
import com.example.eternity_bridge_backend.image.service.ImageService;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.dto.GetPetsResponse;
import com.example.eternity_bridge_backend.pet.entity.Pet;
import com.example.eternity_bridge_backend.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final ImageService imageService;
    private final PetRepository petRepository;


    // 반려동물을 등록한다.
    @Transactional
    public Long createPet(CreatePetRequest request, Long memberId) {
        String trxKey = MDC.get("trxKey");

        checkDuplicatedPet(request, memberId, trxKey);
        log.info("[{}] 반려동물 등록 전 중복체크 결과 => 중복없음", trxKey);

        Pet pet = petRepository.save(request.from(memberId));
        log.info("[{}] 반려동물 등록 성공", trxKey);

        imageService.modifyDomainId(pet.getProfileImageUrl(), memberId, pet.getId());
        log.info("[{}] 이미지 엔티티의 도메인 ID 변경 성공", trxKey);

        return pet.getId();
    }


    // 반려동물을 조회한다.
    public Pet getPet(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new CommonException(PetErrorCode.PET_NOT_FOUND));
    }


    // 사용자의 반려동물을 조회한다.
    public List<GetPetsResponse> getMyPets(Long memberId) {
        String trxKey = MDC.get("trxKey");

        return Optional.ofNullable(petRepository.findMyPets(memberId))
                .filter(pets -> {
                    boolean hasPets = !pets.isEmpty();
                    log.info("[{}] 등록된 반려동물이 {}.", trxKey, hasPets ? "있음" : "없음");
                    return hasPets;
                })
                .orElseThrow(() -> new CommonException(PetErrorCode.MEMBER_HAS_NO_PETS));
    }


    // 반려동물 등록 시 중복체크를 진행한다.
    private void checkDuplicatedPet(CreatePetRequest request, Long memberId, String trxKey) {
        if (petRepository.checkDuplicatedPet(request, memberId)) {
            log.info("[{}] 반려동물 등록 전 중복체크 결과 => 중복있음", trxKey);
            throw new CommonException(PetErrorCode.DUPLICATED_PET);
        }
    }

}
