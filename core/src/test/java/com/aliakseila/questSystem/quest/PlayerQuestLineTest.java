package com.aliakseila.questSystem.quest;

import com.aliakseila.questSystem.core.QuestSystemCoreApplication;
import com.aliakseila.questSystem.core.service.person.NpcService;
import com.aliakseila.questSystem.core.service.person.PlayerService;
import com.aliakseila.questSystem.core.service.quest.questLine.PlayerQuestLineService;
import com.aliakseila.questSystem.core.service.quest.questLine.QuestLineService;
import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.PlayerQuestLine;
import com.aliakseila.questSystem.model.entity.quest.questLine.QuestLineStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = QuestSystemCoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class PlayerQuestLineTest {

    @Autowired
    protected PlayerService playerService;
    @Autowired
    protected NpcService npcService;
    @Autowired
    protected QuestLineService questLineService;
    @Autowired
    protected PlayerQuestLineService playerQuestLineService;

    @BeforeEach
    public void init(){
        Player player = playerService.getOrCreate("alex", 0.0);
        Npc npc = npcService.getOrCreate("bob", 100.0);
        QuestLine questLine = new QuestLine();
        questLine.setOwner(npc);
        questLineService.save(questLine);
    }

    @Test
    void shouldCreateAndSaveQuestLineNode(){
        Player player = playerService.getOrCreate("alex", 0.0);
        Npc npc = npcService.getOrCreate("bob", 100.0);
        QuestLine questLine = questLineService.getQuestLinesByNpcId(npc.getId()).stream().findFirst().orElseThrow();
        PlayerQuestLine playerQuestLine = playerQuestLineService.getOrCreate(player, questLine);
        Assertions.assertEquals(QuestLineStatus.IN_PROGRESS, playerQuestLine.getStatus());
    }

    @Test
    void shouldGetQuestLineNodeByQuestLineId(){
        Npc npc = npcService.getOrCreate("bob", 100.0);
        QuestLine questLine = questLineService.getQuestLinesByNpcId(npc.getId()).stream().findFirst().orElseThrow();
        List<PlayerQuestLine> playerQuestLine = playerQuestLineService.getByQuestLineId(questLine.getId());
        Assertions.assertEquals(1, playerQuestLine.size());
        Assertions.assertEquals(QuestLineStatus.IN_PROGRESS, playerQuestLine.get(0).getStatus());
    }
    @Test
    void shouldGetQuestLineNodeByPlayerId(){
        Player player = playerService.getOrCreate("alex", 0.0);
        List<QuestLine> playerQuestLine = playerQuestLineService.getByPlayerId(player.getId());
        Assertions.assertEquals(1, playerQuestLine.size());
//        Assertions.assertEquals(QuestLineStatus.IN_PROGRESS, playerQuestLine.get(0).getStatus());
    }
}
