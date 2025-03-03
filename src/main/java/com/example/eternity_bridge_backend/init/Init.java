package com.example.eternity_bridge_backend.init;

import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.service.MemorialHallService;
import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.service.PetService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

//@Configuration
@RequiredArgsConstructor
public class Init {

    private final PetService petService;
    private final MemorialHallService memorialHallService;


    @PostConstruct
    public void init() {
        createDummyMemorialHall();
    }


    private void createDummyMemorialHall() {
        for (int i = 1; i <= 200; i++) {
            CreatePetRequest petRequest = new CreatePetRequest(String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i));
            CreateMemorialHallRequest memorialHallRequest = new CreateMemorialHallRequest((long) i);

            petService.createPet(petRequest, (long) i);
            memorialHallService.createMemorialHall(memorialHallRequest, (long) i);
        }
    }

}
