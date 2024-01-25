package com.aliakseila.questSystem.model.entity.quest.event;

import com.aliakseila.questSystem.model.entity.quest.Quest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue(value = "com.aliakseila.questSystem.model.entity.quest.event.QuestEvent")
public class QuestEvent extends Event {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_event_id")
    private DialogueEvent nextEvent;

}
