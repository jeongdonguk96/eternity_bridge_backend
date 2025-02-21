package com.example.eternity_bridge_backend.person.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonRepositoryQuerydslImpl implements PersonRepositoryQuerydsl {

    private final JPAQueryFactory jpaQueryFactory;
}
