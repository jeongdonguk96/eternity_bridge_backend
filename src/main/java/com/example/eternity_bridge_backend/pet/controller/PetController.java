package com.example.eternity_bridge_backend.pet.controller;

import com.example.eternity_bridge_backend.common.dto.CommonResult;
import com.example.eternity_bridge_backend.common.service.ResponseService;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final ResponseService responseService;


    // 반려동물을 등록한다.
    @PostMapping()
    public CommonResult createPet(
            @RequestBody CreatePetRequest request
    ) {
        Long memberId = 1L;
        petService.createPet(request, memberId);
        return responseService.getSuccessResult();
    }


    // 반려동물을 조회한다.
    @GetMapping("/{petId}")
    public CommonResult getPet(
            @PathVariable(name = "petId") Long petId
    ) {
        return responseService.getSingleResult(petService.getPet(petId));
    }


    // 사용자의 반려동물을 조회한다.
    @GetMapping("/my-pets")
    public CommonResult getMyPets() {
        Long memberId = 1L;
        return responseService.getListResult(petService.getMyPets(memberId));
    }

}
