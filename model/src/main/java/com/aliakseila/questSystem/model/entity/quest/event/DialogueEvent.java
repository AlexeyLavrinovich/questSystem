package com.aliakseila.questSystem.model.entity.quest.event;

import com.aliakseila.questSystem.model.entity.quest.event.dialogue.Dialogue;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue(value = "com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent")
public class DialogueEvent extends Event {

    @OneToOne
    @JoinColumn(name = "dialogue_id")
    private Dialogue dialogue;
}
