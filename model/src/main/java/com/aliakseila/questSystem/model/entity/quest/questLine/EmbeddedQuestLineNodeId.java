package com.aliakseila.questSystem.model.entity.quest.questLine;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmbeddedQuestLineNodeId {

    @JoinColumn(name = "quest_line_id")
    private Long questLineId;
    @JoinColumn(name = "player_id")
    private Long playerId;

}
