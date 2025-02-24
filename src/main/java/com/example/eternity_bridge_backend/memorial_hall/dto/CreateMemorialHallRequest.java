package com.example.eternity_bridge_backend.memorial_hall.dto;


import com.example.eternity_bridge_backend.memorial_hall.entity.MemorialHall;

public record CreateMemorialHallRequest(
        Long petId
) {

    public MemorialHall from(Long memberId) {
        return MemorialHall.builder()
                .petId(this.petId)
                .memberId(memberId)
                .build();
    }
}
