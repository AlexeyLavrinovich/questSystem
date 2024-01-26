package com.aliakseila.questSystem.core.service.quest;

import com.aliakseila.questSystem.core.repository.quest.QuestLineRepo;
import com.aliakseila.questSystem.core.repository.quest.QuestRepo;
import com.aliakseila.questSystem.core.service.event.DialogueEventService;
import com.aliakseila.questSystem.core.service.event.QuestEventService;
import com.aliakseila.questSystem.model.entity.quest.GatherQuest;
import com.aliakseila.questSystem.model.entity.quest.KillQuest;
import com.aliakseila.questSystem.model.entity.quest.Quest;
import com.aliakseila.questSystem.model.entity.quest.QuestLine;
import com.aliakseila.questSystem.model.entity.quest.event.DialogueEvent;
import com.aliakseila.questSystem.model.entity.quest.event.Event;
import com.aliakseila.questSystem.model.entity.quest.event.QuestEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestLineService {

    private final QuestLineRepo questLineRepo;
    private final DialogueEventService dialogueEventService;
    private final QuestEventService questEventService;
    private final QuestRepo questRepo;
    private final KillQuestService killQuestService;
    private final GatherQuestService gatherQuestService;


    public void deleteAll() {
        questLineRepo.deleteAll();
    }

    public QuestLine save(QuestLine questLine) {
        return questLineRepo.save(questLine);
    }

    public List<QuestLine> getQuestLinesByPlayerId(Long npcId){
        return questLineRepo.findByOwnerIdAndExecutorIsNull(npcId);
    }

    public QuestLine trigger(QuestLine questLine){
        return dialogueEventService.trigger(questLine.getEvent(), questLine);
    }

    public QuestLine chooseOption(Long dialogueOptionId, QuestLine questLine) {
        Event nextEvent = dialogueEventService.chooseOption(dialogueOptionId);
        if(dialogueEventService.isDialogueEvent(nextEvent.getId())){
            DialogueEvent event = dialogueEventService.findById(nextEvent.getId());
            return dialogueEventService.trigger(event, questLine);
        }
        QuestEvent event = questEventService.findById(nextEvent.getId());
        return questEventService.trigger(event, questLine);
    }

    public boolean checkForQuest(QuestLine questLine) {
        List<Quest> history = questRepo.findByQuestLineId(questLine.getId());
        if(!history.isEmpty()){
            history.forEach(h -> System.out.printf("%d %s %f%n",h.getId(), h.getName(), h.getPrize()));
        }
        return !history.isEmpty();
    }

    public QuestLine passQuest(Long questId, QuestLine questLine, boolean condition) {
        if (condition){
            System.out.println("quest with id " + questId + " was passed");
            if (killQuestService.isKillQuest(questId)){
                KillQuest quest = killQuestService.findById(questId);
                killQuestService.passQuest(quest);
                questLine.getHistory().remove(quest);
                return questLine;
            }
            GatherQuest quest = gatherQuestService.findById(questId);
            gatherQuestService.passQuest(quest);
            questLine.getHistory().remove(quest);
            return questLine;
        }
        return questLine;
    }
}
