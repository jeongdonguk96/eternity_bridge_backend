package com.example.eternity_bridge_backend.pet.dto;


import com.example.eternity_bridge_backend.pet.entity.Pet;

public record CreatePetRequest(
        String name,
        String nickname,
        String petType,
        String birthDate,
        String deathDate,
        String profileImageUrl,
        String dotImageUrl
) {

    public Pet from(Long memberId) {
        return Pet.builder()
                .name(this.name)
                .nickname(this.nickname)
                .petType(this.petType)
                .birthDate(this.birthDate)
                .deathDate(this.deathDate)
                .profileImageUrl(this.profileImageUrl)
                .dotImageUrl(this.dotImageUrl)
                .build();
    }
}
