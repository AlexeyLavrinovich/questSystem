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

    @Test
    void checkQuestLinePassedSuccessfully() {
        Player player = playerService.getByUsername("alex");
        Assertions.assertNotNull(player);

        Npc bob = npcService.getByUsername("bob");
        Assertions.assertNotNull(bob);

        QuestLine questLine = speakWithNpc(player, bob);
        Assertions.assertNotNull(questLine);

        questLine = triggerEvent(questLine);
        questLine = chooseOption(2L, questLine);
        Assertions.assertFalse(checkForQuest(questLine));

        questLine = chooseOption(3L, questLine);
        Assertions.assertTrue(checkForQuest(questLine));

        questLine = passQuest(1L, questLine, true);
        Assertions.assertFalse(checkForQuest(questLine));

        questLine = triggerEvent(questLine);
        questLine = chooseOption(7L, questLine);
        Assertions.assertFalse(checkForQuest(questLine));

        questLine = chooseOption(8L, questLine);
        Assertions.assertTrue(checkForQuest(questLine));
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
        return questLineService.save(questLine);
    }

    private QuestLine triggerEvent(QuestLine questLine){
        return questLineService.save(questLineService.trigger(questLine));
    }

    private QuestLine chooseOption(Long dialogueOptionId, QuestLine questLine){
        return questLineService.save(questLineService.chooseOption(dialogueOptionId, questLine));
    }

    private boolean checkForQuest(QuestLine questLine){
        return questLineService.checkForQuest(questLine);
    }

    private QuestLine passQuest(Long questId, QuestLine questLine, Boolean condition){
        return questLineService.save(questLineService.passQuest(questId, questLine, condition));
    }

}
