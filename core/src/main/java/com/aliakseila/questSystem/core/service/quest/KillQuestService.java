package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.KillQuestRepo;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KillQuestService implements QuestService<KillQuest> {

    private final KillQuestRepo killQuestRepo;

    @Override
    public KillQuest create(String name, double prize) {
        KillQuest killQuest = new KillQuest();
        killQuest.setName(name);
        killQuest.setPrize(prize);
        return killQuest;
    }

    @Override
    public KillQuest save(KillQuest quest) {
        return killQuestRepo.save(quest);
    }

    @Override
    public void deleteAll() {
        killQuestRepo.deleteAll();
    }
}
