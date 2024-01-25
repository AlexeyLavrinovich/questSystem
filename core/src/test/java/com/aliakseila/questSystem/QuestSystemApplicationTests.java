package com.aliakseila.questSystem;

import com.aliakseila.questSystem.core.QuestSystemCoreApplication;
import com.aliakseila.questSystem.core.service.dialogue.DialogueOptionService;
import com.aliakseila.questSystem.core.service.dialogue.DialogueService;
import com.aliakseila.questSystem.core.service.event.DialogueEventService;
import com.aliakseila.questSystem.core.service.event.QuestEventService;
import com.aliakseila.questSystem.core.service.item.ItemService;
import com.aliakseila.questSystem.core.service.person.NpcService;
import com.aliakseila.questSystem.core.service.person.PlayerService;
import com.aliakseila.questSystem.core.service.quest.GatherQuestService;
import com.aliakseila.questSystem.core.service.quest.KillQuestService;
import com.aliakseila.questSystem.core.service.quest.QuestLineService;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest(classes = QuestSystemCoreApplication.class)
class QuestSystemApplicationTests {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private NpcService npcService;
    @Autowired
    private QuestLineService questLineService;
    @Autowired
    private DialogueService dialogueService;
    @Autowired
    private DialogueEventService dialogueEventService;
    @Autowired
    private DialogueOptionService dialogueOptionService;
    @Autowired
    private QuestEventService questEventService;
    @Autowired
    private KillQuestService killQuestService;
    @Autowired
    private GatherQuestService gatherQuestService;
    @Autowired
    private ItemService itemService;

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
        firstFightEvent = questEventService.save(firstFightEvent);

        DialogueEvent d1 = new DialogueEvent();
        DialogueEvent d2 = new DialogueEvent();
        DialogueEvent d3 = new DialogueEvent();
        DialogueEvent d4 = new DialogueEvent();
        d1 = dialogueEventService.save(d1);
        d2 = dialogueEventService.save(d2);
        d3 = dialogueEventService.save(d3);
        d4 = dialogueEventService.save(d4);
        firstFightEvent.setQuest(firstFight);
        firstFightEvent.setNextEvent(d4);
        d1.setDialogue(dialogueService.createAndSave(
                "Hi! You're new here so I can help you.",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.createAndSave("skip tutorial", firstFightEvent),
                        dialogueOptionService.createAndSave("(continue to listen)", d2)
                ))));
        d2.setDialogue(dialogueService.createAndSave(
                "(tell you about Kill quest )",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.createAndSave("skip tutorial", firstFightEvent),
                        dialogueOptionService.createAndSave("(continue to listen)", d3)
                ))));
        d3.setDialogue(dialogueService.createAndSave(
                "(tell you about fight)",
                new ArrayList<>(Collections.singletonList(
                        dialogueOptionService.createAndSave("let's fight", firstFightEvent)
                ))));

        QuestEvent gatherQuestEvent = new QuestEvent();
        GatherQuest gatherQuest = gatherQuestService.create("gatherQuest", 0.0);
        gatherQuest.setItem(itemService.create("dragon tear"));
        gatherQuest = gatherQuestService.save(gatherQuest);
        gatherQuestEvent = questEventService.save(gatherQuestEvent);

        DialogueEvent d5 = new DialogueEvent();
        dialogueEventService.save(d5);
        d4.setDialogue(dialogueService.createAndSave(
                "(tell you about Gather quest)",
                new ArrayList<>(Arrays.asList(
                        dialogueOptionService.createAndSave("skip tutorial", gatherQuestEvent),
                        dialogueOptionService.createAndSave("(continue to listen)", d5)
                ))));
        d5.setDialogue(dialogueService.createAndSave(
                "(tell you about fight)",
                new ArrayList<>(Collections.singletonList(
                        dialogueOptionService.createAndSave("I'll find it", gatherQuestEvent)
                ))));

        QuestLine tutor = new QuestLine();
        tutor.setEvent(d1);
        tutor.setOwner(bob);
        questLineService.save(tutor);

    }

    @AfterEach
    public void done() {
        killQuestService.deleteAll();
        gatherQuestService.deleteAll();
        questLineService.deleteAll();
        dialogueOptionService.deleteAll();
        dialogueService.deleteAll();
        questEventService.deleteAll();
        itemService.deleteAll();
        playerService.deleteAll();
        npcService.deleteAll();
        dialogueEventService.deleteAll();
    }

    @Test
    void checkQuestLinePassedSuccessfully() {
        Player player = playerService.getByUsername("alex");
        Assertions.assertEquals("alex", player.getUsername());
    }

}
