package com.aliakseila.questSystem.model.entity.quest.questLine;

import com.aliakseila.questSystem.model.entity.person.Player;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_player_quest_line")
public class PlayerQuestLine {

    @EmbeddedId
    private EmbeddedQuestLineNodeId id;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @MapsId("questLineId")
    @ToString.Exclude
    private QuestLine questLine;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @MapsId("playerId")
    @ToString.Exclude
    private Player player;

    @Enumerated(EnumType.STRING)
    private QuestLineStatus status;

}
