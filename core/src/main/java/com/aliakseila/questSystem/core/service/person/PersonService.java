package com.aliakseila.questSystem.core.service.person;

import com.aliakseila.questSystem.model.entity.person.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonService<T extends Person> {
    T save(T person);

    T getOrCreate(String name, double sum);

    T createAndSave(String name, double sum);

    T getByUsername(String name);

    void deleteAll();
}
