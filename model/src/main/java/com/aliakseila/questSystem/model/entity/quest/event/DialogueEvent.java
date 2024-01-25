package com.aliakseila.questSystem.model.entity.quest.event;

import com.aliakseila.questSystem.model.entity.quest.event.dialogue.Dialogue;
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
@DiscriminatorValue(value = "com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent")
public class DialogueEvent extends Event {

    @OneToOne
    @JoinColumn(name = "dialogue_id")
    private Dialogue dialogue;
}
