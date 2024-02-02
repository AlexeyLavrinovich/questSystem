package com.aliakseila.questSystem.core.repository.quest.questLine;

import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestLineRepo extends JpaRepository<QuestLine, Long> {

    List<QuestLine> findByOwnerId(Long ownerId);
}
