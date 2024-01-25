package com.aliakseila.questSystem.core.repository.person;

import com.aliakseila.questSystem.model.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo<T extends Person> extends JpaRepository<T, Long> {

    Optional<T> findByUsername(String name);

}
