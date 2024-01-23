package com.aliakseila.questSystem.model.entity.quest.event;

import com.aliakseila.questSystem.model.entity.quest.Quest;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "com.aliakseila.questSystem.model.entity.quest.event.QuestEvent")
public class QuestEvent extends Event {

    @OneToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @OneToOne
    @JoinColumn(name = "next_event_id")
    private Event nextEvent;

}
