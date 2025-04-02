package com.example.eternity_bridge_backend.item.repository;

import com.example.eternity_bridge_backend.item.dto.CreateItemRequest;
import com.example.eternity_bridge_backend.item.dto.GetItemsRequest;
import com.example.eternity_bridge_backend.item.entity.Item;
import com.example.eternity_bridge_backend.item.enums.ItemType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.example.eternity_bridge_backend.item.entity.QItem.item;

@RequiredArgsConstructor
public class ItemRepositoryQuerydslImpl implements ItemRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Item> getItems(String cursorValue, GetItemsRequest request, Pageable pageable) {
        List<Item> itemList = queryFactory.selectFrom(item)
                .where(doesItemEquals(cursorValue, request))
                .orderBy(item.name.asc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        long totalCount = Optional.ofNullable(
                queryFactory.select(item.count())
                        .from(item)
                        .where(doesItemTypeEquals(request.itemType()))
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(itemList, pageable, totalCount);
    }


    @Override
    public boolean checkDuplicatedItem(CreateItemRequest request) {
        return queryFactory.selectOne()
                .from(item)
                .where(item.name.eq(request.name()))
                .fetchFirst() != null;
    }


    private BooleanBuilder doesItemEquals(String cursorValue, GetItemsRequest request) {
        BooleanBuilder builder = new BooleanBuilder();
        return builder
                .and(doesItemTypeEquals(request.itemType()))
                .and(doesItemNameGt(cursorValue));
    }


    private BooleanExpression doesItemTypeEquals(ItemType itemType) {
        return itemType != null ? item.itemType.eq(itemType) : null;
    }


    private BooleanExpression doesItemNameGt(String name) {
        return name != null ? item.name.gt(name) : null;
    }

}