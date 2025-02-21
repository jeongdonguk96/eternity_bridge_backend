package com.example.eternity_bridge_backend.pet.controller;

import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;


    // 강아지를 등록한다.
    @PostMapping()
    public void createPet(@RequestBody CreatePetRequest request) {
        Long memberId = 1L;
        petService.createPet(request, memberId);
    }

}
