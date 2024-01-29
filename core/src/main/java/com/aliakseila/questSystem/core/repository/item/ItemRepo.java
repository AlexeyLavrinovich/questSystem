package com.aliakseila.questSystem.core.repository.item;

import com.aliakseila.questSystem.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
