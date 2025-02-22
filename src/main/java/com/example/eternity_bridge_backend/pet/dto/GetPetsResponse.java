package com.example.eternity_bridge_backend.pet.dto;

import com.querydsl.core.annotations.QueryProjection;


public record GetPetsResponse(
        String name,
        String nickname,
        String petType,
        String birthDate,
        String deathDate,
        String profileImageUrl,
        String dotImageUrl
) {

    @QueryProjection
    public GetPetsResponse {}

}
