package com.example.eternity_bridge_backend.memorial_hall.service;

import com.example.eternity_bridge_backend.common.AbstractTest;
import com.example.eternity_bridge_backend.common.dto.CursorResult;
import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

class MemorialHallServiceTest extends AbstractTest {

    @Autowired EntityManager em;


    @Test
    @DisplayName("추모관이 정상적으로 등록된다.")
    void createMemorialHallTest() {
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


    @Test
    @DisplayName("추모관이 정상적으로 조회된다.")
    void getMemorialHallTest() {
        // given
        createDummyMemorialHall();

        // when
        CursorResult<GetMemorialHallsResponse> memorialHallList = memorialHallService.getMemorialHallsBySlice(180L, CustomSort.DESC, PageRequest.of(0, 10));

        // then
        for (GetMemorialHallsResponse memorialHallsResponse : memorialHallList.getValueList()) {
            System.out.println("memorialHallsResponse = " + memorialHallsResponse);
        }
        assertThat(memorialHallList.getValueList().getSize()).isEqualTo(10);
        assertThat(memorialHallList.getHasNext()).isTrue();
        assertThat(memorialHallList.getValueList().getContent().get(0).id()).isEqualTo(179L);
    }


    void createDummyMemorialHall() {
        for (int i = 1; i <= 200; i++) {
            CreatePetRequest petRequest = new CreatePetRequest(String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i));
            CreateMemorialHallRequest memorialHallRequest = new CreateMemorialHallRequest((long) i);

            petService.createPet(petRequest, (long) i);
            memorialHallService.createMemorialHall(memorialHallRequest, (long) i);
        }
    }

}