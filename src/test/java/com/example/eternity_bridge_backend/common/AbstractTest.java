package com.example.eternity_bridge_backend.common;

import com.example.eternity_bridge_backend.memorial_hall.repository.MemorialHallRepository;
import com.example.eternity_bridge_backend.memorial_hall.service.MemorialHallService;
import com.example.eternity_bridge_backend.pet.repository.PetRepository;
import com.example.eternity_bridge_backend.pet.service.PetService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public abstract class AbstractTest {

    private static final Set<String> entities = Set.of(
            "pet", "item", "member", "memorial_hall"
    );


    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired EntityManager em;

    @Autowired public PetService petService;
    @Autowired public MemorialHallService memorialHallService;

    @Autowired public PetRepository petRepository;
    @Autowired public MemorialHallRepository memorialHallRepository;

    @AfterEach
    void cleanUp() {
        // 1. 영속성 컨텍스트에 남겨진 SQL을 실행한다.
        em.flush();

        // 2. 테이블 간의 참조 무결성을 비활성화한다.
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        // 3. 모든 테이블의 데이터를 삭제하고 ID 값을 1로 초기화한다.
        for (String entity : entities) {
            em.createNativeQuery("TRUNCATE TABLE " + entity).executeUpdate();
            em.createNativeQuery("ALTER TABLE " + entity + " AUTO_INCREMENT = 1").executeUpdate();
        }

        // 4. 참조 무결성을 활성화한다.
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

}
