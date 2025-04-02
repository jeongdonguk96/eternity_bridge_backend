package com.example.eternity_bridge_backend.image.repository;

import com.example.eternity_bridge_backend.image.entity.Image;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.example.eternity_bridge_backend.image.entity.QImage.image;

@RequiredArgsConstructor
public class ImageRepositoryQuerydslImpl implements ImageRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<Image> getImage(String url, Long memberId) {
        return Optional.ofNullable(queryFactory.selectFrom(image)
                .where(image.url.eq(url)
                        .and(image.memberId.eq(memberId)))
                .fetchOne());
    }
}
