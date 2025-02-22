package com.example.eternity_bridge_backend.member.repository;

import com.example.eternity_bridge_backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQuerydsl {
}
