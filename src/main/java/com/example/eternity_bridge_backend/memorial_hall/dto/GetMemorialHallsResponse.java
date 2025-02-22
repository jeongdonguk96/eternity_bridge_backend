package com.example.eternity_bridge_backend.memorial_hall.dto;

import com.querydsl.core.annotations.QueryProjection;


public record GetMemorialHallsResponse(
    Long id,
    Long petId,
    String petName,
    String petNickname,
    String petType,
    String petBirthDate,
    String petDeathDate,
    String petProfileImageUrl,
    String petDotImageUrl
) {

    @QueryProjection
    public GetMemorialHallsResponse {}

}
