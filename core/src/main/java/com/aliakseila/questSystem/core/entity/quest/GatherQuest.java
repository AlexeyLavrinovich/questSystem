package com.aliakseila.questSystem.core.entity.quest;


import com.aliakseila.questSystem.core.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("com.aliakseila.serializeJavaObjects.core.entity.quest.GatherQuest")
public class GatherQuest extends Quest{

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public void passTheQuest() {
        item.setOwner(executor);
        super.passTheQuest();
        item.setOwner(creator);
    }
}
