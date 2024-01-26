package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.person.PersonRepo;
import com.aliakseila.questSystem.core.repository.quest.KillQuestRepo;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.person.Status;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class KillQuestService implements QuestService<KillQuest> {

    private final KillQuestRepo killQuestRepo;
    private final PersonRepo personRepo;

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

    @Override
    public KillQuest findById(Long questId) {
        return killQuestRepo.findById(questId).orElseThrow();
    }

    public boolean isKillQuest(Long questId) {
        return killQuestRepo.existsById(questId);
    }

    public void passQuest(KillQuest quest){
        Person person = quest.getTarget();
        person.setStatus(Collections.singleton(Status.DEAD));
        personRepo.save(person);
        quest.setQuestLine(null);
        save(quest);
    }
}
