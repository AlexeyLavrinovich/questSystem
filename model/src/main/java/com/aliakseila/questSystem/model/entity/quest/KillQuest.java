package com.aliakseila.questSystem.model.entity.quest;


import com.aliakseila.questSystem.model.entity.person.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.quest.KillQuest")
public class KillQuest extends Quest {

    @OneToOne
    private Person target;

}
