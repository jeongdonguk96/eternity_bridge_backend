package com.example.eternity_bridge_backend.memorial_hall.dto;

import com.querydsl.core.annotations.QueryProjection;


public record GetMemorialHallResponse(
    Long id,
    Long petId,
    Long memberId,
    String petName,
    String petNickname,
    String petType,
    String petBirthDate,
    String petDeathDate,
    String petProfileImageUrl,
    String petDotImageUrl
) {

    @QueryProjection
    public GetMemorialHallResponse {}

}
