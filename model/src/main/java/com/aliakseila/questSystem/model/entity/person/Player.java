package com.aliakseila.questSystem.model.entity.person;

import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.person.Player")
public class Player extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executor")
    private List<QuestLine> questLines;

}
