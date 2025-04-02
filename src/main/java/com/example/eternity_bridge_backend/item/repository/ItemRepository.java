package com.example.eternity_bridge_backend.item.repository;

import com.example.eternity_bridge_backend.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryQuerydsl {
}
