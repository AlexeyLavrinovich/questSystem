package com.aliakseila.questSystem.model.entity.quest;

import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;

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
    private Event event;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questLine")
    private LinkedList<Quest> history;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @ToString.Exclude
    private Npc owner;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    @ToString.Exclude
    private Player executor;

}
