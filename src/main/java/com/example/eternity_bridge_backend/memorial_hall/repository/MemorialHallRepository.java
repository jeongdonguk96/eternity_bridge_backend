package com.example.eternity_bridge_backend.memorial_hall.repository;

import com.example.eternity_bridge_backend.memorial_hall.entity.MemorialHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemorialHallRepository extends JpaRepository<MemorialHall, Long>, MemorialHallRepositoryQuerydsl {
}
