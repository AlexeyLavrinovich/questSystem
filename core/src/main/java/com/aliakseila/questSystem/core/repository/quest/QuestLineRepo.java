package com.aliakseila.questSystem.core.repository.quest;

import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestLineRepo extends JpaRepository<QuestLine, Long> {
}
