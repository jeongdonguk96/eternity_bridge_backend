package com.example.eternity_bridge_backend.member.repository;

import com.example.eternity_bridge_backend.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.eternity_bridge_backend.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryQuerydslImpl implements MemberRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isDuplicatedEmail(String email) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst() != null;
    }

    @Override
    public Member findByEmail(String email) {
        return queryFactory
                .selectFrom(member)
                .where(member.email.eq(email))
                .fetchFirst();
    }

}