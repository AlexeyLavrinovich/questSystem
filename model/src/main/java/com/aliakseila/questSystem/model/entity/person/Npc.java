package com.aliakseila.questSystem.model.entity.person;

import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.person.Npc")
public class Npc extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<QuestLine> questLines;

}
