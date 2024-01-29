package com.aliakseila.questSystem.core.service.event;

import com.aliakseila.questSystem.core.repository.event.ExchangeEventRepo;
import com.aliakseila.questSystem.core.service.item.ItemService;
import com.aliakseila.questSystem.core.service.item.PocketsService;
import com.aliakseila.questSystem.core.service.person.NpcService;
import com.aliakseila.questSystem.core.service.person.PlayerService;
import com.aliakseila.questSystem.model.entity.Item;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.ExchangeEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeEventService implements EventService<ExchangeEvent> {

    private final ExchangeEventRepo exchangeEventRepo;
    private final ItemService itemService;
    private final PocketsService pocketsService;

    @Override
    public ExchangeEvent save(ExchangeEvent event) {
        return exchangeEventRepo.save(event);
    }

    @Override
    public void deleteAll() {
        exchangeEventRepo.deleteAll();
    }

    @Override
    public QuestLine trigger(ExchangeEvent event, QuestLine questLine) {
        Npc npc = questLine.getOwner();
        Pockets npcPockets = npc.getPockets();
        npcPockets.setMoney(npcPockets.getMoney() + event.getSum());
        List<Item> npcItems = npcPockets.getItems();
        npcItems.remove(event.getItem());
        npcPockets.setItems(npcItems);
        pocketsService.save(npcPockets);

        Player player = questLine.getExecutor();
        Pockets playerPockets = player.getPockets();
        playerPockets.setMoney(playerPockets.getMoney() - event.getSum());
        List<Item> playerItems = playerPockets.getItems();
        Item item = event.getItem();
        item.setOwner(playerPockets);
        item = itemService.save(item);
        playerItems.add(item);
        playerPockets.setItems(playerItems);
        pocketsService.save(playerPockets);
        System.out.printf("You spent %f ducats and get %s%n", event.getSum(), event.getItem());
        return questLine;
    }

    @Override
    public ExchangeEvent findById(Long id) {
        return exchangeEventRepo.findById(id).orElseThrow();
    }

    public boolean isExchangeEvent(Long id) {
        return exchangeEventRepo.existsById(id);
    }

    public ExchangeEvent createAndSave(double sum, Item item) {
        return save(new ExchangeEvent(sum, item));
    }
}
