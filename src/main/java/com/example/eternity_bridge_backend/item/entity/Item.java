package com.example.eternity_bridge_backend.item.entity;

import com.example.eternity_bridge_backend.common.entity.BaseEntity;
import com.example.eternity_bridge_backend.item.dto.CreateItemRequest;
import com.example.eternity_bridge_backend.item.enums.ItemType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    private String name;
    private String url;


    public void modifyItem(CreateItemRequest request) {
        this.itemType = request.itemType();
        this.name = request.name();
        this.url = request.url();
    }
}
