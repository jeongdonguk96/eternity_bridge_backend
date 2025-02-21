package com.example.eternity_bridge_backend.pet.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PetRepositoryQuerydslImpl implements PetRepositoryQuerydsl {

    private final JPAQueryFactory jpaQueryFactory;
}
