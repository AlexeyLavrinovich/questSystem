package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import org.springframework.stereotype.Service;

@Service
public interface QuestService<T extends Quest> {
    T save (T quest);

    T create(String name, double prize);

    void deleteAll();
}
