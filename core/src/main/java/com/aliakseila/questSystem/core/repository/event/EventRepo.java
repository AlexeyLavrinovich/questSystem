package com.aliakseila.questSystem.core.repository.event;

import com.aliakseila.questSystem.model.entity.quest.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo <T extends Event> extends JpaRepository<T, Long> {
}
