package com.example.eternity_bridge_backend.pet.repository;

import com.example.eternity_bridge_backend.pet.dto.CreatePetRequest;
import com.example.eternity_bridge_backend.pet.dto.GetPetsResponse;
import com.example.eternity_bridge_backend.pet.dto.QGetPetsResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.eternity_bridge_backend.pet.entity.QPet.pet;

@RequiredArgsConstructor
public class PetRepositoryQuerydslImpl implements PetRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<GetPetsResponse> findMyPets(Long memberId) {
        return queryFactory
                .select(new QGetPetsResponse(
                        pet.name,
                        pet.nickname,
                        pet.petType,
                        pet.birthDate,
                        pet.deathDate,
                        pet.profileImageUrl,
                        pet.dotImageUrl
                ))
                .from(pet)
                .where(pet.memberId.eq(memberId))
                .fetch();
    }


    @Override
    public boolean checkDuplicatedPet(CreatePetRequest request, Long memberId) {
        return queryFactory.selectOne()
                .from(pet)
                .where(pet.name.eq(request.name())
                        .and(pet.memberId.eq(memberId)))
                .fetchFirst() != null;
    }

}
