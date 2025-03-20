package com.example.eternity_bridge_backend.member.repository;

import com.example.eternity_bridge_backend.member.entity.Member;

public interface MemberRepositoryQuerydsl {
    boolean isDuplicatedEmail(String email);
    Member findByEmail(String email);
}
