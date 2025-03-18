package com.example.eternity_bridge_backend.pet.service;

import com.example.eternity_bridge_backend.common.AbstractTest;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.dto.GetPetsResponse;
import com.example.eternity_bridge_backend.pet.entity.Pet;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PetServiceTest extends AbstractTest {

    @Autowired EntityManager em;


    @Test
    @DisplayName("반려동물이 정상적으로 등록된다.")
    void createPetTest() {
        // given
        Long memberId = 1L;
        CreatePetRequest request = new CreatePetRequest("보나", "사랑", "진돗개", "2021-08-01", "2025-02-07", "", "");

        // when
        petService.createPet(request, memberId);

        // then
        assertThat(petRepository.findAll().size()).isEqualTo(1);
        assertThat(petRepository.findById(1L).get().getName()).isEqualTo("보나");
    }


    @Test
    @DisplayName("반려동물이 정상적으로 조회된다.")
    void getPetTest() {
        // given
        Long memberId = 1L;
        CreatePetRequest request1 = new CreatePetRequest("보나", "사랑", "진돗개", "2021-08-01", "2025-02-07", "", "");
        CreatePetRequest request2 = new CreatePetRequest("안나", "귀요미", "고양이", "2021-08-01", "2025-02-07", "", "");
        petService.createPet(request1, memberId);
        petService.createPet(request2, memberId);

        // when
        Pet pet = petService.getPet(2L);

        // then
        assertThat(pet.getName()).isEqualTo("안나");
        assertThat(pet.getNickname()).isEqualTo("귀요미");
    }


    @Test
    @DisplayName("사용자의 반려동물이 정상적으로 조회된다.")
    void getMyPetsTest() {
        // given
        Long memberId1 = 1L;
        Long memberId2 = 2L;
        CreatePetRequest request1 = new CreatePetRequest("보나", "사랑", "진돗개", "2021-08-01", "2025-02-07", "", "");
        CreatePetRequest request2 = new CreatePetRequest("안나", "귀요미", "고양이", "2021-08-01", "2025-02-07", "", "");
        petService.createPet(request1, memberId1);
        petService.createPet(request2, memberId2);

        // when
        List<GetPetsResponse> myPets = petService.getMyPets(1L);

        // then
        assertThat(myPets.size()).isEqualTo(1);
        assertThat(myPets.get(0).name()).isEqualTo("보나");
    }

}