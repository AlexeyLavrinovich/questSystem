package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.item.ItemRepo;
import com.aliakseila.questSystem.core.repository.quest.GatherQuestRepo;
import com.aliakseila.questSystem.model.entity.Item;
import com.aliakseila.questSystem.model.entity.person.Person;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GatherQuestService implements QuestService<GatherQuest> {

    private final GatherQuestRepo gatherQuestRepo;
    private final ItemRepo itemRepo;

    @Override
    public GatherQuest save(GatherQuest quest) {
        return gatherQuestRepo.save(quest);
    }

    @Override
    public GatherQuest create(String name, double prize) {
        GatherQuest gatherQuest = new GatherQuest();
        gatherQuest.setName(name);
        gatherQuest.setPrize(prize);
        return gatherQuest;
    }

    @Override
    public void deleteAll() {
        gatherQuestRepo.deleteAll();
    }

    @Override
    public GatherQuest findById(Long questId) {
        return gatherQuestRepo.findById(questId).orElseThrow();
    }

    @Override
    public void passQuest(GatherQuest quest) {
        Item item = quest.getItem();
        item.setOwner(quest.getQuestLine().getOwner().getPockets());
        itemRepo.save(item);
        quest.setQuestLine(null);
        save(quest);
    }
}
