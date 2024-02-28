package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.GatherQuestRepo;
import com.aliakseila.questSystem.core.service.item.ItemService;
import com.aliakseila.questSystem.core.service.item.PocketsService;
import com.aliakseila.questSystem.core.service.quest.questLine.PlayerQuestLineService;
import com.aliakseila.questSystem.model.entity.Item;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import com.aliakseila.questSystem.model.entity.quest.questLine.PlayerQuestLine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GatherQuestService implements QuestService<GatherQuest> {

    private final GatherQuestRepo gatherQuestRepo;
    private final ItemService itemService;
    private final PocketsService pocketsService;
    private final PlayerQuestLineService playerQuestLineService;

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
        Npc npc = quest.getQuestLine().getOwner();
        Pockets npcPockets = npc.getPockets();
        Item item = quest.getItem();
        item.setOwner(npcPockets);
        item = itemService.save(item);
        List<Item> npcPocketsItems = npcPockets.getItems();
        npcPocketsItems.add(item);
        npcPockets.setItems(npcPocketsItems);
        pocketsService.save(npcPockets);
        Player player = playerQuestLineService.getByQuestLineId(quest.getQuestLine().getId())
                .stream()
                .map(PlayerQuestLine::getPlayer)
                .findFirst()
                .orElseThrow();
        Pockets pockets = player.getPockets();
        pockets.setMoney(pockets.getMoney() + quest.getPrize());
        player.setPockets(pockets);
        pocketsService.save(pockets);
        quest.setQuestLine(null);
        save(quest);
    }
}
