package com.aliakseila.questSystem.model.entity.quest.questLine;

import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_quest_line")
public class QuestLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private DialogueEvent event;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "questLine")
    private List<Quest> history;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "owner_id")
    @ToString.Exclude
    private Npc owner;
//    @ManyToOne(cascade = {CascadeType.REMOVE})
//    @JoinColumn(name = "executor_id")
//    @ToString.Exclude
//    private Player executor;

    @OneToMany(mappedBy = "questLine")
    private List<PlayerQuestLine> playerQuestLine;

}
