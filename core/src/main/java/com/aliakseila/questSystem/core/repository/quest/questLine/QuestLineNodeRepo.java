package com.aliakseila.questSystem.core.repository.quest.questLine;

import com.aliakseila.questSystem.model.entity.quest.questLine.EmbeddedQuestLineNodeId;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLineNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestLineNodeRepo extends JpaRepository<QuestLineNode, EmbeddedQuestLineNodeId> {

    List<QuestLineNode> findAllById_QuestLineId(Long questLineId);
    List<QuestLineNode> findAllById_PlayerId(Long playerId);

}
