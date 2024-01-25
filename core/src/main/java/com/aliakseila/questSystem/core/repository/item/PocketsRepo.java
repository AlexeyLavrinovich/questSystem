package com.aliakseila.questSystem.core.repository.item;

import com.aliakseila.questSystem.model.entity.person.Pockets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocketsRepo extends JpaRepository<Pockets, Long> {
}
