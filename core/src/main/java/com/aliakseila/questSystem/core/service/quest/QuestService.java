package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.QuestRepo;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestService<T extends Quest> {
    T save (T quest);

    T create(String name, double prize);

    void deleteAll();

    T findById(Long questId);

    void passQuest(T quest);
}
