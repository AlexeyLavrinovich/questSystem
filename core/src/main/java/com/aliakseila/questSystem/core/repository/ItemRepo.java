package com.aliakseila.questSystem.core.repository;

import com.aliakseila.questSystem.core.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
}
