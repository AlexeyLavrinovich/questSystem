package com.aliakseila.questSystem.script;

import com.aliakseila.questSystem.core.QuestSystemCoreApplication;
import com.aliakseila.questSystem.core.service.dialogue.DialogueOptionService;
import com.aliakseila.questSystem.core.service.dialogue.DialogueService;
import com.aliakseila.questSystem.core.service.event.DialogueEventService;
import com.aliakseila.questSystem.core.service.event.ExchangeEventService;
import com.aliakseila.questSystem.core.service.event.QuestEventService;
import com.aliakseila.questSystem.core.service.item.ItemService;
import com.aliakseila.questSystem.core.service.person.NpcService;
import com.aliakseila.questSystem.core.service.person.PlayerService;
import com.aliakseila.questSystem.core.service.quest.GatherQuestService;
import com.aliakseila.questSystem.core.service.quest.KillQuestService;
import com.aliakseila.questSystem.core.service.quest.questLine.PlayerQuestLineService;
import com.aliakseila.questSystem.core.service.quest.questLine.QuestLineService;
import com.aliakseila.questSystem.model.entity.Item;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.person.Pockets;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import com.aliakseila.questSystem.model.entity.quest.event.ExchangeEvent;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = QuestSystemCoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class ScriptedQuestLineBaseTest {

    @Autowired
    protected PlayerService playerService;
    @Autowired
    protected NpcService npcService;
    @Autowired
    protected QuestLineService questLineService;
    @Autowired
    protected DialogueService dialogueService;
    @Autowired
    protected DialogueEventService dialogueEventService;
    @Autowired
    protected DialogueOptionService dialogueOptionService;
    @Autowired
    protected QuestEventService questEventService;
    @Autowired
    protected KillQuestService killQuestService;
    @Autowired
    protected GatherQuestService gatherQuestService;
    @Autowired
    protected ItemService itemService;
    @Autowired
    protected ExchangeEventService exchangeEventService;
    @Autowired
    protected PlayerQuestLineService playerQuestLineService;

    @BeforeEach
    public void init() {
        Player player = playerService.createAndSave("alex", 200.0);
        Npc john = npcService.createAndSave("john", 10);
        Npc bob = npcService.createAndSave("bob", 100);
        Npc bandit = npcService.createAndSave("erik", 2);
        Npc firstFightTarget = npcService.createAndSave("trim", 0);

        QuestEvent firstFightEvent = new QuestEvent();
        KillQuest firstFight = killQuestService.create("first fight", 0.0);
        firstFight.setTarget(firstFightTarget);
        firstFight = killQuestService.save(firstFight);
        firstFightEvent.setQuest(firstFight);
        firstFightEvent = questEventService.save(firstFightEvent);

        DialogueEvent d1 = new DialogueEvent();
        DialogueEvent d2 = new DialogueEvent();
        DialogueEvent d3 = new DialogueEvent();
        DialogueEvent d4 = new DialogueEvent();
        d1 = dialogueEventService.save(d1);
        d2 = dialogueEventService.save(d2);
        d3 = dialogueEventService.save(d3);
        d4 = dialogueEventService.save(d4);
        firstFightEvent.setNextEvent(d4);
        firstFightEvent = questEventService.save(firstFightEvent);
        d1.setDialogue(dialogueService.createAndSave(
                "Hi! You're new here so I can help you.",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.create("skip tutorial", firstFightEvent),
                        dialogueOptionService.create("(continue to listen)", d2)
                ))));
        d2.setDialogue(dialogueService.createAndSave(
                "(tell you about Kill quest )",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.create("skip tutorial", firstFightEvent),
                        dialogueOptionService.create("(continue to listen)", d3)
                ))));
        d3.setDialogue(dialogueService.createAndSave(
                "(tell you about fight)",
                new ArrayList<>(Collections.singletonList(
                        dialogueOptionService.create("let's fight", firstFightEvent)
                ))));

        QuestEvent gatherQuestEvent = new QuestEvent();
        GatherQuest gatherQuest = gatherQuestService.create("gatherQuest", 250.0);
        Item dragonTear = itemService.createAndSave("dragon tear", john.getPockets());
        gatherQuest.setItem(dragonTear);
        gatherQuest = gatherQuestService.save(gatherQuest);
        gatherQuestEvent.setQuest(gatherQuest);
        gatherQuestEvent = questEventService.save(gatherQuestEvent);

        DialogueEvent d5 = new DialogueEvent();
        dialogueEventService.save(d5);
        d4.setDialogue(dialogueService.createAndSave(
                "(tell you about Gather quest)",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.create("skip tutorial", gatherQuestEvent),
                        dialogueOptionService.create("(continue to listen)", d5)
                ))));
        d5.setDialogue(dialogueService.createAndSave(
                "(tell you about dragon tear)",
                new ArrayList<>(Collections.singletonList(
                        dialogueOptionService.create("I'll find it", gatherQuestEvent)
                ))));

        QuestLine tutor = new QuestLine();
        tutor.setEvent(d1);
        tutor.setOwner(bob);
        dialogueEventService.save(d1);
        dialogueEventService.save(d2);
        dialogueEventService.save(d3);
        dialogueEventService.save(d4);
        dialogueEventService.save(d5);
        questLineService.save(tutor);

        QuestEvent killJohnEvent = new QuestEvent();
        KillQuest killJohn = killQuestService.create("kill john", 0.0);
        killJohn.setTarget(john);
        killJohn = killQuestService.save(killJohn);
        killJohnEvent.setQuest(killJohn);
        killJohnEvent = questEventService.save(killJohnEvent);

        ExchangeEvent exchangeEvent = exchangeEventService.createAndSave(200.0, dragonTear);

        DialogueEvent d6 = new DialogueEvent();
        DialogueEvent d7 = new DialogueEvent();
        d6 = dialogueEventService.save(d6);
        d7 = dialogueEventService.save(d7);
        d6.setDialogue(dialogueService.createAndSave(
                "I'll sell you dragon tear for 300 ducats",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.create("Oh, really? My sword doesn't agree with your price", d7),
                        dialogueOptionService.create("I'll take them from your corpse", killJohnEvent)
                ))
        ));
        d7.setDialogue(dialogueService.createAndSave(
                "Don't get angry! I'll sell you dragon tear for 200 ducats. That's my last offer",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.create("This price was worth starting with", exchangeEvent),
                        dialogueOptionService.create("I'll take them from your corpse", killJohnEvent)
                ))
        ));
        d6 = dialogueEventService.save(d6);
        d7 = dialogueEventService.save(d7);

        QuestLine getDragonTear = new QuestLine();
        getDragonTear.setEvent(d6);
        getDragonTear.setOwner(john);
        questLineService.save(getDragonTear);
    }

    protected Player lootNpc(Player player, Npc npc) {
        Pockets playerPockets = player.getPockets();
        Pockets npcPockets = npc.getPockets();

        playerPockets.setMoney(playerPockets.getMoney() + npcPockets.getMoney());

        List<Item> items = playerPockets.getItems();
        items.addAll(npcPockets.getItems());
        playerPockets.setItems(items);
        player.setPockets(playerPockets);

        npcPockets.setMoney(0.0);
        npcPockets.setItems(null);
        npc.setPockets(npcPockets);
        npcService.save(npc);
        return playerService.save(player);
    }

    protected boolean checkForDragonTear(Player player) {
        return player.getPockets().getItems()
                .stream()
                .anyMatch(i -> i.equals(itemService.findByName("dragon tear")));
    }

    protected QuestLine speakWithNpc(Player player, Npc npc) {
        List<QuestLine> playerQuests = playerService.getQuestLines(player);
        QuestLine questLine = questLineService.getQuestLinesByNpcId(npc.getId()).stream()
                .findFirst()
                .orElseThrow();
        Assertions.assertNotNull(questLine);
        if(!playerQuests.contains(questLine)){
            playerQuestLineService.getOrCreate(player, questLine);
        }
        return questLineService.save(questLine);
    }

    protected QuestLine triggerEvent(QuestLine questLine){
        return questLineService.save(questLineService.trigger(questLine));
    }

    protected QuestLine chooseOption(Long dialogueOptionId, QuestLine questLine){
        return questLineService.save(questLineService.chooseOption(dialogueOptionId, questLine));
    }

    protected boolean checkForQuest(QuestLine questLine){
        return questLineService.checkForQuest(questLine);
    }

    protected QuestLine passQuest(Long questId, QuestLine questLine, Boolean condition){
        return questLineService.save(questLineService.passQuest(questId, questLine, condition));
    }
}
