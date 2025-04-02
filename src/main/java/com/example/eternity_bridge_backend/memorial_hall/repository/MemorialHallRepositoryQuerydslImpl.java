package com.example.eternity_bridge_backend.memorial_hall.repository;

import com.example.eternity_bridge_backend.memorial_hall.dto.CreateMemorialHallRequest;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.GetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.QGetMemorialHallResponse;
import com.example.eternity_bridge_backend.memorial_hall.dto.QGetMemorialHallsResponse;
import com.example.eternity_bridge_backend.memorial_hall.enums.CustomSort;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.OptimisticLockException;
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
    public Slice<GetMemorialHallsResponse> findAllByCursorValue(Long cursorValue, CustomSort sort, Pageable pageable) {
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
                .where(getCursorCondition(cursorValue, sort))
                .orderBy(getOrderSpecifier(sort))
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, memorialList);
    }

    @Override
    public GetMemorialHallResponse getMemorialHall(Long id) {
        GetMemorialHallResponse response = queryFactory.select(new QGetMemorialHallResponse(
                        memorialHall.id,
                        pet.id,
                        pet.memberId,
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
                .where(memorialHall.id.eq(id))
                .fetchOne();

        // 조회수 증가 로직
        int maxRetryAttempts = 3;
        int retryCount = 0;

        // 재시도 로직을 while문으로 감싸서 최대 재시도 횟수까지 반복한다.
        while (retryCount < maxRetryAttempts) {
            try {
                updateViewCount(id);
                break;
            } catch (OptimisticLockException e) {
                updateViewCount(id);
                break;
//                retryCount++;
//                if (retryCount >= maxRetryAttempts) {
//                    break;
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                    break;
//                }
            }
        }

        return response;
    }


    @Override
    public void updateViewCount(Long id) {
        queryFactory
                .update(memorialHall)
                .set(memorialHall.viewCount, memorialHall.viewCount.add(1))
                .where(memorialHall.id.eq(id))
                .execute();
    }


    @Override
    public boolean checkDuplicatedMemorialHall(CreateMemorialHallRequest request, Long memberId) {
        return queryFactory.selectOne()
                .from(memorialHall)
                .where(memorialHall.petId.eq(request.petId()))
                .fetchFirst() != null;
    }


    // cursorId의 여부를 확인한 다음, 조회 기준에 따라 데이터를 반환한다.
    private BooleanExpression getCursorCondition(Long cursorValue, CustomSort sort) {
        if (cursorValue == null) {
            return null;
        }

        return switch (sort) {
            case ASC -> memorialHall.id.gt(cursorValue);
            case DESC -> memorialHall.id.lt(cursorValue);
            case VIEWS -> memorialHall.viewCount.lt(cursorValue);
            default -> null;
        };
    }


    // 정렬 기준에 따라 데이터를 정렬한다.
    private OrderSpecifier<?> getOrderSpecifier(CustomSort sort) {
        return switch (sort) {
            case ASC -> memorialHall.id.asc();
            case DESC -> memorialHall.id.desc();
            case VIEWS -> memorialHall.viewCount.desc();
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
