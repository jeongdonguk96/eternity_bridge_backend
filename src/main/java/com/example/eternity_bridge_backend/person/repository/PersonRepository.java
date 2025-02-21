package com.example.eternity_bridge_backend.person.repository;

import com.example.eternity_bridge_backend.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuerydsl {
}
