package com.example.eternity_bridge_backend.memorial.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemorialRepositoryQuerydslImpl implements MemorialRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

}
