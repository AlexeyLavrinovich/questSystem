package com.aliakseila.questSystem.core.repository.quest.questLine;

import com.aliakseila.questSystem.model.entity.quest.questLine.EmbeddedQuestLineNodeId;
import com.aliakseila.questSystem.model.entity.quest.questLine.PlayerQuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerQuestLineRepo extends JpaRepository<PlayerQuestLine, EmbeddedQuestLineNodeId> {

    List<PlayerQuestLine> findAllById_QuestLineId(Long questLineId);

    @Query(value = "SELECT pql.questLine FROM PlayerQuestLine pql where pql.id.playerId = :playerId")
    List<QuestLine> findQuestLineById_PlayerId(Long playerId);

}
