package com.example.eternity_bridge_backend.image.repository;

import com.example.eternity_bridge_backend.image.entity.Image;

import java.util.Optional;

public interface ImageRepositoryQuerydsl {
    Optional<Image> getImage(String url, Long memberId);
}
