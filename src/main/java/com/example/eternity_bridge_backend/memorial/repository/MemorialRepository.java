package com.example.eternity_bridge_backend.memorial.repository;

import com.example.eternity_bridge_backend.memorial.entity.Memorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemorialRepository extends JpaRepository<Memorial, Long>, MemorialRepositoryQuerydsl {
}
