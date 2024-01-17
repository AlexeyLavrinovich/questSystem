package com.aliakseila.questSystem.model.entity.quest;


import com.aliakseila.questSystem.model.entity.Status;
import com.aliakseila.questSystem.model.entity.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.util.Collections;

@Entity
@DiscriminatorValue("com.aliakseila.serializeJavaObjects.core.entity.quest.KillQuest")
public class KillQuest extends Quest{

    @OneToOne
    private User target;

    @Override
    public void passTheQuest() {
        target.setStatus(Collections.singleton(Status.DEAD));
        super.passTheQuest();
    }
}
