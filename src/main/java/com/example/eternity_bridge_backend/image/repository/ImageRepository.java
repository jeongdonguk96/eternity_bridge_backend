package com.example.eternity_bridge_backend.image.repository;

import com.example.eternity_bridge_backend.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryQuerydsl {
}
