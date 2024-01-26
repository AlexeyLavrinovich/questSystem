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
import com.aliakseila.questSystem.model.entity.quest.event.dialogue.DialogueOption;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = QuestSystemCoreApplication.class)
class QuestSystemApplicationTests extends BaseApplicationTest {


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

//    @Override
//    @BeforeEach
//    public void init(){
//        super.init();
//    }

    @Test
    void checkQuestLinePassedSuccessfully() {
        Player player = playerService.getByUsername("alex");
        Npc bob = npcService.getByUsername("bob");
        QuestLine tutor = speakWithNpc(player, bob);


    }

    private QuestLine speakWithNpc(Player player, Npc npc) {
        List<QuestLine> playerQuests = player.getQuestLines();
        QuestLine questLine = questLineService.getQuestLinesByPlayerId(npc.getId()).stream()
                .findFirst()
                .orElseThrow();
        Assertions.assertNotNull(questLine);
        playerQuests.add(questLine);
        player.setQuestLines(playerQuests);
        questLine.setExecutor(player);
        questLine = questLineService.save(questLineService.trigger(questLine));
        questLine = questLineService.save(questLineService.chooseOption(2L, questLine));
        Assertions.assertFalse(questLineService.checkForQuest(questLine));
        questLine = questLineService.save(questLineService.chooseOption(3L, questLine));
        Assertions.assertTrue(questLineService.checkForQuest(questLine));
        questLine = questLineService.save(questLineService.passQuest(1L, questLine, true));
        Assertions.assertFalse(questLineService.checkForQuest(questLine));
        questLine = questLineService.save(questLineService.trigger(questLine));
        questLine = questLineService.save(questLineService.chooseOption(7L, questLine));
        Assertions.assertFalse(questLineService.checkForQuest(questLine));
        questLine = questLineService.save(questLineService.chooseOption(8L, questLine));
        Assertions.assertTrue(questLineService.checkForQuest(questLine));
        return questLine;
    }

}
