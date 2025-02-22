package com.example.eternity_bridge_backend.memorial_hall.repository;

import com.example.eternity_bridge_backend.common.enums.CustomSort;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.QGetMemorialHallsResponse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.example.eternity_bridge_backend.memorial_hall.entity.QMemorialHall.memorialHall;
import static com.example.eternity_bridge_backend.pet.entity.QPet.pet;

@RequiredArgsConstructor
public class MemorialHallRepositoryQuerydslImpl implements MemorialHallRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;


    @Override
    public Slice<GetMemorialHallsResponse> findAllByCursorId(Long cursorId, CustomSort sort, Pageable pageable) {
        List<GetMemorialHallsResponse> memorialList = queryFactory.select(new QGetMemorialHallsResponse(
                        memorialHall.id,
                        pet.id,
                        pet.name,
                        pet.nickname,
                        pet.petType,
                        pet.birthDate,
                        pet.deathDate,
                        pet.profileImageUrl,
                        pet.dotImageUrl
                ))
                .from(memorialHall)
                .leftJoin(pet).on(memorialHall.petId.eq(pet.id))
                .fetchJoin()
                .where(getCursorCondition(cursorId, sort))
                .orderBy(getOrderSpecifier(sort))
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, memorialList);
    }


    // cursorId의 여부를 확인한 다음, 조회 기준에 따라 데이터를 반환한다.
    private BooleanExpression getCursorCondition(Long cursorId, CustomSort sort) {
        if (cursorId == null) {
            return null;
        }

        return switch (sort) {
            case ASC -> memorialHall.id.gt(cursorId);
            case DESC -> memorialHall.id.lt(cursorId);
            default -> null;
        };
    }


    // 정렬 기준에 따라 데이터를 정렬한다.
    private OrderSpecifier<?> getOrderSpecifier(CustomSort sort) {
        return switch (sort) {
            case ASC -> memorialHall.id.asc();
            case DESC -> memorialHall.id.desc();
            default -> null;
        };
    }


    // Slice 객체를 반환하여 무한 스크롤을 처리한다.
    private Slice<GetMemorialHallsResponse> checkLastPage(Pageable pageable, List<GetMemorialHallsResponse> memorialList) {
        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 true, 작으면 false
        if (memorialList.size() > pageable.getPageSize()) {
            memorialList.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(memorialList, pageable, hasNext);
    }

}
