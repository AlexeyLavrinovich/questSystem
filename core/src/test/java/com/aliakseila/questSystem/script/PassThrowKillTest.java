package com.aliakseila.questSystem.script;

import com.aliakseila.questSystem.model.entity.person.Npc;
import com.aliakseila.questSystem.model.entity.person.Player;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PassThrowKillTest extends ScriptedQuestLineBaseTest {

    @Test
    void checkTutorialQuestLinePassThrowKillTest() {
        Player player = playerService.getByUsername("alex");
        Assertions.assertNotNull(player);

        Npc bob = npcService.getByUsername("bob");
        Assertions.assertNotNull(bob);

        Npc john = npcService.getByUsername("john");
        Assertions.assertNotNull(john);

        QuestLine tutorial = speakWithNpc(player, bob);
        Assertions.assertNotNull(tutorial);

        tutorial = triggerEvent(tutorial);
        tutorial = chooseOption(2L, tutorial);
        Assertions.assertFalse(checkForQuest(tutorial));

        tutorial = chooseOption(3L, tutorial);
        Assertions.assertTrue(checkForQuest(tutorial));

        tutorial = passQuest(1L, tutorial, true);
        Assertions.assertFalse(checkForQuest(tutorial));

        tutorial = triggerEvent(tutorial);
        tutorial = chooseOption(7L, tutorial);
        Assertions.assertFalse(checkForQuest(tutorial));

        tutorial = chooseOption(8L, tutorial);
        Assertions.assertTrue(checkForQuest(tutorial));

        QuestLine getDragonTear = speakWithNpc(player, john);
        Assertions.assertNotNull(getDragonTear);

        getDragonTear = triggerEvent(getDragonTear);
        getDragonTear = chooseOption(9L, getDragonTear);
        Assertions.assertFalse(checkForQuest(getDragonTear));

        getDragonTear = chooseOption(12L, getDragonTear);
        Assertions.assertTrue(checkForQuest(getDragonTear));

        getDragonTear = passQuest(3L, getDragonTear, true);
        Assertions.assertFalse(checkForQuest(getDragonTear));

        player = lootNpc(player, john);
        Assertions.assertTrue(checkForDragonTear(player));

        tutorial = passQuest(2L, tutorial, true);
        Assertions.assertFalse(checkForQuest(tutorial));

        player = playerService.getByUsername(player.getUsername());
        Assertions.assertTrue(player.getPockets().getMoney() > 260.0);

        player = playerService.getByUsername(player.getUsername());
        Assertions.assertFalse(checkForDragonTear(player));
    }

}
