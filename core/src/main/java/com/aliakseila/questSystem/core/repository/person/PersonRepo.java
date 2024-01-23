package com.aliakseila.questSystem.core.repository.person;

import com.aliakseila.questSystem.model.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo<T extends Person> extends JpaRepository<T, Long> {

    Person findByUsername(String name);

}
