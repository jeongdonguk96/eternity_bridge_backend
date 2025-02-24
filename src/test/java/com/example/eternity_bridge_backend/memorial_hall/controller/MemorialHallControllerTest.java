package com.example.eternity_bridge_backend.memorial_hall.controller;

import com.example.eternity_bridge_backend.common.AbstractTest;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class MemorialHallControllerTest extends AbstractTest {

    @Autowired EntityManager em;


    @Test
    @DisplayName("추모관이 정상적으로 등록된다.")
    void createPetTest() {
        // given
        Long memberId = 1L;
        CreatePetRequest petRequest = new CreatePetRequest("보나", "사랑", "진돗개", "2021-08-01", "2025-02-07", "", "");
        petService.createPet(petRequest, memberId);
        CreateMemorialHallRequest request = new CreateMemorialHallRequest(1L);

        // when
        memorialHallService.createMemorialHall(request, memberId);

        // then
        assertThat(memorialHallRepository.findAll().size()).isEqualTo(1);
        assertThat(memorialHallRepository.findById(1L).get().getPetId()).isEqualTo(1L);
        assertThat(memorialHallRepository.findById(1L).get().getMemberId()).isEqualTo(1L);
    }

}