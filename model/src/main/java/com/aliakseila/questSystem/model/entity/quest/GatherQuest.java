package com.aliakseila.questSystem.model.entity.quest;


import com.aliakseila.questSystem.model.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("com.aliakseila.questSystem.model.entity.quest.GatherQuest")
public class GatherQuest extends Quest {

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
